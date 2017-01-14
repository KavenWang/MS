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
package com.hoperun.rdc.test;

import java.util.Date;
import java.util.Iterator;

import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.hoperun.rdc.utils.GitHelper;


/** 
 * ClassName: TestGitHelper <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: Oct 24, 2016 2:06:31 PM <br/> 
 * 
 * @author yin_changbao 
 * @version  
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING )
public class TestGitHelper {

	@Before
	public void init() {
		// nothing to do.
	}

	
	@Test
	public void test2Pull(){
		Assert.assertTrue(GitHelper.pull( "D:/jgti/test/", "stable-4.4"));
	}
	
	@Test
	public void test3LogCmd(){
		Iterable<RevCommit> ite = GitHelper.log("D:/jgti/test/", 1);
		Assert.assertNotNull(ite);
		Iterator<RevCommit> iteor = ite.iterator();
		while(iteor.hasNext()){
			RevCommit rev = iteor.next();
			System.out.println(rev.getFullMessage()+"|"+rev.getCommitterIdent().getName()+"|"+rev.getId().name()+"|"+new Date(rev.getCommitTime()*1000L));
		}
			
		
			
	}
}
