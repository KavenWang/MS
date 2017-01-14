package com.hoperun.rdc.engines;

import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoperun.rdc.constants.REConstaints;
import com.hoperun.rdc.exceptions.DroolsException;
import com.hoperun.rdc.exceptions.code.ExceptionCodeEnum;
import com.hoperun.rdc.fact.IFact;
import com.hoperun.rdc.utils.FileUtil;
import com.hoperun.rdc.utils.GitHelper;
import com.hoperun.rdc.utils.HoperunDroolsUtils;
import com.hoperun.rdc.utils.Serializer;
import com.hoperun.rdc.utils.StringUtil;

/**
 * 
 * @ClassName: FileSystemEngine
 * @Description: note instance of this class can't be singleton, otherwise will
 *               course concurrent problem.<scope="prototype or request">
 * @author YinChang-bao
 * @date Nov 9, 2015 11:11:37 AM
 *
 */
public class FileSystemEngine implements IDroolsRuleEngine {

	private static final Logger logger = LoggerFactory.getLogger(FileSystemEngine.class);

	private KieContainer container;

	EngineConfiguration config;

	/**
	 * Creates a new instance of FileSystemEngine.
	 */
	public FileSystemEngine(EngineConfiguration config) {
		super();
		this.config = config;
		if (!config.useGitAsRepository)
			init(this.config.ruleBase, this.config.indicator);
		else
			init();
	}

	public FileSystemEngine() {
		super();
	}

	public FileSystemEngine(String ruleBase) {
		super();
		this.config.ruleBase = ruleBase;
	}

	@Override
	public void init() {
		
		GitHelper.pull(this.config.ruleBase, this.config.lable);
		if (!StringUtil.isEmpty(this.config.versionId))
			GitHelper.hardReset(this.config.ruleBase, this.config.versionId);
		init(GitHelper.parseLocalResp(this.config.ruleBase), this.config.indicator);
	}

	@Override
	public void init(String ruleBase, String indicator) {

		logger.info("FileSystemEngine initing...with rulebase: {}, and indicator: {} ", ruleBase, indicator);
		KieServices kieService = KieServices.Factory.get();
		KieFileSystem kfs = kieService.newKieFileSystem();
		List<File> files = config.files==null||config.files.isEmpty()?FileUtil.iterateFolder(ruleBase + File.separator + config.indicator, null):config.files;
		if (files == null || files.isEmpty())
			throw new DroolsException(ExceptionCodeEnum.fsDroolsRuleBaseNotExists);
		for (File file : files) {
			String fileName = file.getAbsolutePath();
			if (!StringUtil.isEmpty(fileName) && fileName.lastIndexOf(".") > -1) {
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				if (suffix.toUpperCase().equals(REConstaints.DROOLS_RULE_SUFIX_DRL.toUpperCase())
						|| suffix.toUpperCase().equals(REConstaints.DROOLS_RULE_SUFIX_TEMPLATE.toUpperCase())
						|| suffix.toUpperCase().equals(REConstaints.DROOLS_RULE_SUFIX_RDRL.toUpperCase())
						|| suffix.toUpperCase().equals(REConstaints.DROOLS_RULE_SUFIX_RDSLR.toUpperCase())) {
					kfs.write(ResourceFactory.newFileResource(file));
				}
			}
		}
		KieBuilder kbuilder = kieService.newKieBuilder(kfs);
		kbuilder.buildAll();
		if (kbuilder.getResults().hasMessages(Level.ERROR))
			throw new IllegalArgumentException(kbuilder.getResults().toString());

		container = kieService.newKieContainer(kbuilder.getKieModule().getReleaseId());

	}

	private void clear() {
		if (container == null)
			return;
		KieBase kieBase = container.getKieBase();

		try {

			Collection<KiePackage> packages = kieBase.getKiePackages();
			for (KiePackage pg : packages) {
				kieBase.removeKiePackage(pg.getName());
			}

		} catch (Exception e) {
			logger.warn("FileSystem engine refresh error, " + e.getMessage(), e);
		} finally {
		}
	}

	private SoftReference<byte[]> reLoadFact(IFact message) {
		if (message == null)
			return null;
		if (!message.getClass().getClassLoader().equals(container.getClassLoader())) {
			logger.warn("ClassLoader Not Match, Changing Data ClassLoader Automaticly");
			return new SoftReference<byte[]>(Serializer.serialize(message));
		} else
			return null;
	}

	private IFact doDesra(KieSession kSession, IFact message) {
		SoftReference<byte[]> sRef = reLoadFact(message);
		if (message != null && sRef != null && sRef.get() != null) {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			ClassLoader drooleLoader = container.getClassLoader();
			Thread.currentThread().setContextClassLoader(drooleLoader);
			Object obj = Serializer.deserialize(sRef.get(), drooleLoader);
			kSession.insert(obj);
			Thread.currentThread().setContextClassLoader(classLoader);
			return (IFact) obj;
			// new SoftReference<byte[]>(Serializer.serialize(fact));
		} else if (message != null && (sRef == null || sRef.get() == null))
			kSession.insert(message);
		return null;

	}

	@Override
	public IFact execute(IFact message) {
		if (message == null) {
			logger.warn("FileSystem engine startup, but nothing to do...");
			return null;
		}
		KieSession kSession = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			ClassLoader drooleLoader = container.getClassLoader();
			Thread.currentThread().setContextClassLoader(drooleLoader);
			SoftReference<byte[]> sRef = reLoadFact(message);
			Serializable obj = Serializer.deserialize(sRef.get(), drooleLoader);
			kSession = container.getKieBase().newKieSession();
			kSession.insert(obj);
			kSession.fireAllRules();
			Thread.currentThread().setContextClassLoader(classLoader);

			return Serializer.deserialize(Serializer.serialize(obj), classLoader);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		} finally {
			if (kSession != null)
				kSession.dispose();
		}
		return null;

	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.hoperun.rdc.engines.IDroolsRuleEngine#execute(com.hoperun.rdc.fact.IFact[])
	 */

	@Override
	@Deprecated
	public <T extends IFact> T[] execute(T[] facts) {
		if (facts == null || facts.length < 1) {
			logger.warn("FileSystem engine startup, but nothing to do...");
			return null;
		}
		KieSession kSession = container.getKieBase().newKieSession();
		for (IFact fact : facts) {
			try {
				fact = doDesra(kSession, fact);
			} catch (Exception e) {
				logger.warn(e.getMessage(), e);
			}
		}
		kSession.fireAllRules();
		return facts;
	}

	@Override
	@Deprecated
	public List<? extends IFact> execute(List<? extends IFact> messages) {
		if (HoperunDroolsUtils.isEmpty(messages)) {
			logger.warn("FileSystem engine startup, but nothing to do...");
			return null;
		}
		KieSession kSession = container.getKieBase().newKieSession();
		for (IFact fact : messages) {
			try {
				fact = doDesra(kSession, fact);
			} catch (Exception e) {
				logger.warn(e.getMessage(), e);
			}
		}
		kSession.fireAllRules();
		return messages;
	}

	@Override
	public void shutdwon() {

		if (container != null) {
			clear();
			container = null;
		}

	}

	/**
	 * @see com.hoperun.rdc.engines.IDroolsRuleEngine#returnNewSession()
	 */
	@Override
	public KieSession returnNewSession() {
		return container.getKieBase().newKieSession();
	}

}
