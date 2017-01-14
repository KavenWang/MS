package com.hoperun.rdc.engines.pool;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoperun.rdc.engines.EngineConfiguration;
import com.hoperun.rdc.engines.IDroolsRuleEngine;
import com.hoperun.rdc.fact.IFact;


/**
 * 
 * ClassName: IPooledDroolsRuleEngine
 * 
 * @description
 * @author yin_changbao
 * @Date Dec 17, 2015
 *
 */
public abstract class AbstractPooledDroolsRuleEngine {

	private static final Logger logger = LoggerFactory.getLogger(AbstractPooledDroolsRuleEngine.class);

	public static final int DEFAULT_MAXTOTAL = 20;
	public static final int DEFAULT_MAXTOTALPERKEY = 5;
	public static final int DEFAULT_MINIDLEPERKEY = 0;

	public static final int DEFAULT_MAXWAITMILLIS = -1;
	protected Pool pool;

	/**
	 * execute with a factor
	 * 
	 * @param messages
	 */
	public IFact execute(EngineConfiguration key,IFact message) {
		IDroolsRuleEngine engine = null;
		try {
			engine = pool.borrowObject(key);
			logger.debug("borrowed an instance from sub-pool:"+key+" | instance: "+engine);
			IFact result =  engine.execute(message);
			logger.debug("engine "+key+" executed, result: "+result);
			
			logger.debug("returning engine to pool....");
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(engine!=null)
				pool.returnObject(key, engine);
		}
		return null;
	}

	/**
	 * execute with a collection of factors
	 * 
	 * @param messages
	 */
	@Deprecated
	public List<? extends IFact> execute(EngineConfiguration key,  List<? extends IFact> messages) {
		IDroolsRuleEngine engine = null;
		try {
			engine = pool.borrowObject(key);
			logger.debug("borrowed an instance from sub-pool:"+key+" | instance: "+engine);
			List<? extends IFact> result =  engine.execute(messages);
			logger.debug("engine "+key+" executed, result: "+result);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(engine!=null)
				pool.returnObject(key, engine);
			logger.debug("returning engine to pool....");
		}
		return null;
	}
	@Deprecated
	public <T extends IFact> T[] execute(EngineConfiguration key,  T... facts) {
		IDroolsRuleEngine engine = null;
		try {
			engine = pool.borrowObject(key);
			logger.debug("borrowed an instance from sub-pool:"+key+" | instance: "+engine);
			T[] result =  engine.execute(facts);
			logger.debug("engine "+key+" executed, result: "+result);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(engine!=null)
				pool.returnObject(key, engine);
			logger.debug("returning engine to pool....");
		}
		return null;
	}


	/**
	 * return engine to engine pool
	 * 
	 * @param droolsRuleEngine
	 */
	public void release(EngineConfiguration key, IDroolsRuleEngine droolsRuleEngine) {
		pool.returnObject(key, droolsRuleEngine);
	}
	
	public KieSession createNewSession(EngineConfiguration key, IDroolsRuleEngine droolsRuleEngine){
		
		IDroolsRuleEngine engine = null;
		try {
			engine = pool.borrowObject(key);
			return engine.returnNewSession();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(engine!=null)
				pool.returnObject(key, engine);
			logger.debug("returning engine to pool....");
		}
		return null;
	}

	
	public abstract void setMaxWaitMillis(long maxWaitMillis);

	public abstract void setMaxTotal(int maxTotal);

	public abstract void setMaxTotalPerKey(int maxTotalPerKey) ;

	public abstract void setMinIdlePerKey(int minIdlePerKey);

}
