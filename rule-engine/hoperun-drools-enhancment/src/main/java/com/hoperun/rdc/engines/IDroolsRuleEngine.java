package com.hoperun.rdc.engines;

import java.util.List;

import org.kie.api.runtime.KieSession;

import com.hoperun.rdc.fact.IFact;


/**
 * 
* @ClassName: PointRuleEngine 
* @Description: 
* @author YinChang-bao
* @date Nov 9, 2015 10:51:43 AM 
*
 */
public interface IDroolsRuleEngine {
	
	
	/**
	 * 
	 * @Description: init engine
	 */
	public void init();
	
	/**
	 * 
	 * @param ruleBase
	 */
	public void init(String ruleBase,String ruleFolder);
	
	
	/**
	 * @Description: refresh rule
	 * @param message com.hoperun.ism.drools.msg.Message instance
	 */
	public IFact execute(IFact message);
	
	
	public void shutdwon();

	@Deprecated
	public List<? extends IFact> execute(List<? extends IFact> messages);

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @return
	 */
	public KieSession returnNewSession();

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param facts
	 * @return
	 */
	@Deprecated
	public <T extends IFact> T[] execute(T[] facts);
}