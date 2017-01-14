package com.hoperun.rdc.engines.pool;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/**
 * 
 * ClassName: Config
 * @description engine pool Configuration
 * @author yin_changbao
 * @Date   Dec 17, 2015
 *
 */
public class Config extends GenericKeyedObjectPoolConfig {
	
	public Config(int maxTotal, int maxTotalPerKey, int minIdlePerKey) {
		this.setMaxTotal(maxTotal);
		this.setMaxTotalPerKey(maxTotalPerKey);
		this.setMinIdlePerKey(minIdlePerKey);
		this.setBlockWhenExhausted(true);
		this.setNumTestsPerEvictionRun(Integer.MAX_VALUE);
		this.setTestOnBorrow(true);
		this.setTestOnReturn(false);
		this.setTestWhileIdle(false);
		this.setTimeBetweenEvictionRunsMillis(1 * 60000L);
		this.setMinEvictableIdleTimeMillis(10 * 60000L);
		this.setTestWhileIdle(false);

	}

}
