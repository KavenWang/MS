package com.hoperun.rdc.engines.pool;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

import com.hoperun.rdc.engines.EngineConfiguration;
import com.hoperun.rdc.engines.IDroolsRuleEngine;

/**
 * 
 * ClassName: Pool
 * 
 * @description engine pool
 * @author yin_changbao
 * @Date Dec 17, 2015
 *
 */
public class Pool extends GenericKeyedObjectPool<EngineConfiguration, IDroolsRuleEngine> {
	
	public Pool(KeyedPooledObjectFactory<EngineConfiguration, IDroolsRuleEngine> factory, Config config) {
		super(factory, config);
	}
}
