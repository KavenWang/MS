package com.hoperun.rdc.engines.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.hoperun.rdc.engines.EngineConfiguration;
import com.hoperun.rdc.engines.IDroolsRuleEngine;


/**
 * 
 * ClassName: AbstractDroolsRuleEngineFactory
 * @description baseKeyedPool is exactly made for our  situation.
 * @author yin_changbao
 * @Date   Dec 17, 2015
 *
 */
public abstract class AbstractDroolsRuleEngineFactory extends BaseKeyedPooledObjectFactory<EngineConfiguration,IDroolsRuleEngine> {

	private static final Log logger = LogFactory.getLog(AbstractDroolsRuleEngineFactory.class);
	
	public abstract IDroolsRuleEngine createDroolsRuleEngine(EngineConfiguration key);
	
	@Override
	public IDroolsRuleEngine create(EngineConfiguration key) throws Exception {
		return createDroolsRuleEngine(key);
	}

	@Override
	public PooledObject<IDroolsRuleEngine> wrap(IDroolsRuleEngine value) {
		return new DefaultPooledObject<IDroolsRuleEngine>(value);
	}
	@Override
	public void destroyObject(EngineConfiguration key, PooledObject<IDroolsRuleEngine> p) throws Exception {
		logger.debug("kill unused object from sub-pool: "+key);
		p.getObject().shutdwon();
	}
}
