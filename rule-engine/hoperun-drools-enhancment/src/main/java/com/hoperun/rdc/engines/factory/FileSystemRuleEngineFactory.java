package com.hoperun.rdc.engines.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.hoperun.rdc.engines.EngineConfiguration;
import com.hoperun.rdc.engines.FileSystemEngine;
import com.hoperun.rdc.engines.IDroolsRuleEngine;


/**
 * 
 * ClassName: FileSystemRuleEngineFactory
 * 
 * @description
 * @author yin_changbao
 * @Date Dec 17, 2015
 *
 */
public class FileSystemRuleEngineFactory extends AbstractDroolsRuleEngineFactory {

	private static final Log logger = LogFactory.getLog(FileSystemRuleEngineFactory.class);

	@Override
	public IDroolsRuleEngine createDroolsRuleEngine(EngineConfiguration key) {
		logger.debug("engine pool creating engine: " + key);
		return new FileSystemEngine(key);
	}


	

}
