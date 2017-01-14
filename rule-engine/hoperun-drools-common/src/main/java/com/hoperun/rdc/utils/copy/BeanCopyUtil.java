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
package com.hoperun.rdc.utils.copy;


import com.hoperun.rdc.utils.copy.IMethodCallBack;

import net.sf.cglib.beans.BeanCopier;
*//** 
 * ClassName: BeanCopyUtil
 * Function: TODO ADD FUNCTION.
 * date: Dec 12, 2016 5:43:58 PM .
 * 
 * @author yin_changbao 
 * @version  
 *//*
public class BeanCopyUtil {

	public static <F,T> void copy(final F  fromBean,final Class<T> toBean,final ) throws Exception{
		new IMethodCallBack<F,T>() {
	        BeanCopier bc = BeanCopier.create(fromBean.getClass(), toBean,
	                false);

	        @Override
	        public T callMethod(F frombean) throws Exception {
	            bc.copy(frombean, toBean, null);
	            return toBean;
	        }


	    }.callMethod(fromBean);
	}

	public static void main(String args[]){
		new SAds()
		BeanCopyUtil.copy(, toBean);
	}
}



*/