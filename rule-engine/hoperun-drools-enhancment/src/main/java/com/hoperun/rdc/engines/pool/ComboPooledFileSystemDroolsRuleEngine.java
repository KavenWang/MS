package com.hoperun.rdc.engines.pool;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoperun.rdc.engines.EngineConfiguration;
import com.hoperun.rdc.engines.IDroolsRuleEngine;
import com.hoperun.rdc.engines.factory.FileSystemRuleEngineFactory;


/**
 * 
 * ClassName: ComboPooledDroolsRuleEngine
 * 
 * @description
 * @author yin_changbao
 * @Date Dec 17, 2015
 *
 */
public class ComboPooledFileSystemDroolsRuleEngine extends AbstractPooledDroolsRuleEngine {

	private static final Logger logger = LoggerFactory.getLogger(ComboPooledFileSystemDroolsRuleEngine.class);
	
	private int maxTotal = DEFAULT_MAXTOTAL;
	private int maxTotalPerKey = DEFAULT_MAXTOTALPERKEY;
	private int minIdlePerKey = DEFAULT_MINIDLEPERKEY;
	private long maxWaitMillis = DEFAULT_MAXWAITMILLIS;

	
	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	@Override
	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	@Override
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	

	public int getMaxTotalPerKey() {
		return maxTotalPerKey;
	}

	@Override
	public void setMaxTotalPerKey(int maxTotalPerKey) {
		this.maxTotalPerKey = maxTotalPerKey;
	}

	public int getMinIdlePerKey() {
		return minIdlePerKey;
	}

	@Override
	public void setMinIdlePerKey(int minIdlePerKey) {
		this.minIdlePerKey = minIdlePerKey;
	}

	public ComboPooledFileSystemDroolsRuleEngine() {
		super();
		KeyedPooledObjectFactory<EngineConfiguration, IDroolsRuleEngine> factory = new FileSystemRuleEngineFactory();
		this.pool = new Pool(factory, new Config(maxTotal, maxTotalPerKey, minIdlePerKey));
		this.pool.setMaxWaitMillis(maxWaitMillis);
	}

	

}
