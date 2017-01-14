/*****************************************************************************
 *
 *                      HOPERUN PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to HopeRun
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from HopeRun.
 *
 *            Copyright (c) 2016 by HopeRun.  All rights reserved.
 *
 *****************************************************************************//*
package com.hoperun.rdc.unittest.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hoperun.rdc.engines.EngineConfiguration;
import com.hoperun.rdc.engines.pool.AbstractPooledDroolsRuleEngine;
import com.hoperun.rdc.fact.IFact;
import com.hoperun.rdc.testCommponent.DemoFact;
import com.hoperun.rdc.testCommponent.RuleEngineCtx;

*//**
 * ClassName: TestPooledEngine <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: Oct 25, 2016 10:49:02 AM <br/>
 * 
 * @author yin_changbao
 * @version
 *//*
public class TestPooledEngine {

	private static final Logger logger = LoggerFactory.getLogger(TestPooledEngine.class);

	static ApplicationContext ctx = null;

	@Before
	public void init() {
		System.setProperty("file.encoding","UTF-8");
		ctx = new AnnotationConfigApplicationContext(RuleEngineCtx.class);
		Assert.assertNotNull(ctx);
		logger.debug("annotation context started");
	}

	@Test
	public void testExecutePooledEngineWithNoGit() {
		AbstractPooledDroolsRuleEngine ruleEgine = ctx.getBean(AbstractPooledDroolsRuleEngine.class);
		IFact result = ruleEgine.execute(new EngineConfiguration(
				"D:/workII/Common-acrh/rule-engine/hoperun-drools-enhancment/src/test/",
				null, "resources", null, false), new DemoFact(130000, 1100000, 18, "CHN"));
		Assert.assertNotNull(result);
		logger.info(result.toString());
	}

}
*/