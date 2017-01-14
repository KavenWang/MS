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

import com.hoperun.rdc.fact.IFact;

/** 
 * ClassName: DemoFact <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: Oct 25, 2016 11:32:59 AM <br/> 
 * 
 * @author yin_changbao 
 * @version  
 */
public class DemoFact implements IFact{
	
	//收入
	private double income;
	
	//固定资产市值
	private double fixedAssets;
	
	//年龄
	private int age;
	
	//国籍
	private String country;
	
	private double accounting;
	
	

	/** 
	 * accounting. 
	 * 
	 * @return  the accounting 
	 */
	public double getAccounting() {
		return accounting;
	}

	/** 
	 * accounting. 
	 * 
	 * @param   accounting    the accounting to set 
	 */
	public void setAccounting(double accounting) {
		this.accounting = accounting;
	}

	/** 
	 * income. 
	 * 
	 * @return  the income 
	 */
	public double getIncome() {
		return income;
	}

	/** 
	 * income. 
	 * 
	 * @param   income    the income to set 
	 */
	public void setIncome(double income) {
		this.income = income;
	}

	/** 
	 * fixedAssets. 
	 * 
	 * @return  the fixedAssets 
	 */
	public double getFixedAssets() {
		return fixedAssets;
	}

	/** 
	 * fixedAssets. 
	 * 
	 * @param   fixedAssets    the fixedAssets to set 
	 */
	public void setFixedAssets(double fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	/** 
	 * age. 
	 * 
	 * @return  the age 
	 */
	public int getAge() {
		return age;
	}

	/** 
	 * age. 
	 * 
	 * @param   age    the age to set 
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/** 
	 * country. 
	 * 
	 * @return  the country 
	 */
	public String getCountry() {
		return country;
	}

	/** 
	 * country. 
	 * 
	 * @param   country    the country to set 
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** 
	 * Creates a new instance of DemoFact. 
	 */  
	public DemoFact(double income, double fixedAssets, int age, String country) {
		super();
		this.income = income;
		this.fixedAssets = fixedAssets;
		this.age = age;
		this.country = country;
	}

	@Override
	public String toString() {
		return "DemoFact [income=" + income + ", fixedAssets=" + fixedAssets + ", age=" + age + ", country=" + country
				+ ", accounting=" + accounting + "]";
	}
	
	

}
