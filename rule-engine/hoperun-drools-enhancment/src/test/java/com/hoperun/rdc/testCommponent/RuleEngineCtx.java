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
 *****************************************************************************/
package com.hoperun.rdc.testCommponent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hoperun.rdc.engines.pool.AbstractPooledDroolsRuleEngine;
import com.hoperun.rdc.engines.pool.ComboPooledFileSystemDroolsRuleEngine;

/** 
 * ClassName: RuleEngineCtx <br/> 
 * Function: 此例演示如何如何在spring中配置 ComboPooledFileSystemDroolsRuleEngine<br>
 * 其中对象池参数，在实际应用项目中可以通过配置参数等形式给出也如此例直接在configuration中写定，等效xml模式直接写值和el读配置 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: Oct 25, 2016 11:10:38 AM <br/> 
 * 
 * @author yin_changbao 
 * @version  
 */
@Configuration
public class RuleEngineCtx {
	
	/**
	 * 
	 * abstractPooledDroolsRuleEngine:(这里用一句话描述这个方法的作用). <br/> 
	 * 
	 * @author yin_changbao 
	 * @return
	 */
	@Bean 
	public AbstractPooledDroolsRuleEngine abstractPooledDroolsRuleEngine(){
		AbstractPooledDroolsRuleEngine ruleEngine = new ComboPooledFileSystemDroolsRuleEngine();
		ruleEngine.setMaxTotal(100);
		ruleEngine.setMaxTotalPerKey(3);
		ruleEngine.setMaxWaitMillis(-1);
		ruleEngine.setMinIdlePerKey(1);
		return ruleEngine;
		
	}


}
