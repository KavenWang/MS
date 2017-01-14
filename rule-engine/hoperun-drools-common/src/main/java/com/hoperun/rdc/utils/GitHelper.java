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
package com.hoperun.rdc.utils;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ClassName: GitHelper <br/>
 * Function: Java端实现git的常见pull、push、clone等命令. <br>
 * 工程中供drools 客户端pull 规则文件使用。主要提供clone 、pull、checkout、log等命令
 * 
 * Date: Oct 21, 2016 11:27:08 AM <br/>
 * 
 * @author yin_changbao
 * @version
 */
public class GitHelper {

	private static final Logger logger = LoggerFactory.getLogger(GitHelper.class);
	
	
	

	public static void createRepositry(String repository) {
		Git git = null;

		try {
			git = Git.init().setDirectory(FileUtil.checkOrmkdirs(repository)).call();

		} catch (IllegalStateException | GitAPIException e) {
			logger.error(e.getMessage(),e);
		}finally{
			if(git!=null)
				git.close();
		}
	}

	public static boolean clone(String remoteRepository, String lable,String user,String password ) {
		if(StringUtil.isEmpty(remoteRepository)){
			logger.error("Romote Url Is Empty");
			System.exit(-1);
		}
		String repository = parseLocalResp(remoteRepository);
		Git git = null;
		try {
			CloneCommand cloneCommand = Git.cloneRepository();
			cloneCommand.setURI(remoteRepository);
			if(!StringUtil.isEmpty(user)&&!StringUtil.isEmpty(password))
				cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider( user, password ));
			else
				logger.info("Git Resporty {} Need No Credential",remoteRepository);
			git = cloneCommand.setDirectory(FileUtil.checkOrmkdirs(repository))
					.call();
			if (!StringUtil.isEmpty(lable))
				git.checkout().setName(lable).setCreateBranch(true).call();
			return true;
		} catch (GitAPIException e) {
			logger.error(e.getMessage(), e);
			
		}finally{
			if(git!=null)
				git.close();
		}
		return false;

	}
	
	public static boolean clone(String remoteRepository, String localPath, String lable,String user,String password ) {
		if(StringUtil.isEmpty(remoteRepository)){
			logger.error("Romote Url Is Empty");
			System.exit(-1);
		}
		Git git = null;
		try {
			CloneCommand cloneCommand = Git.cloneRepository();
			cloneCommand.setURI(remoteRepository);
			if(!StringUtil.isEmpty(user)&&!StringUtil.isEmpty(password))
				cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider( user, password ));
			else
				logger.info("Git Resporty {} Need No Credential",remoteRepository);
			git = cloneCommand.setDirectory(FileUtil.checkOrmkdirs(localPath))
					.call();
			if (!StringUtil.isEmpty(lable))
				git.checkout().setName(lable).setCreateBranch(true).call();
			return true;
		} catch (GitAPIException e) {
			logger.error(e.getMessage(), e);
			
		}finally{
			if(git!=null)
				git.close();
		}
		return false;

	}

	/**
	 * pull: do git comment pull,
	 * 
	 * @author yin_changbao
	 * @param ruleBase
	 * @param object
	 */
	public static boolean pull(String repository, String lable,String user,String password) {
		String localResp = parseLocalResp(repository);
		File f = new File(localResp);
		if(!f.exists())
			clone(repository, localResp, lable, user,password);
		Git git = null;
		try {
			git = Git.open(FileUtil.checkOrmkdirs(localResp));
			String branchName = git.getRepository().getBranch();
			if (!StringUtil.isEmpty(lable)&&!lable.equals(branchName))
				git.checkout().setName(lable).setCreateBranch(true).call();
			PullCommand command = git.pull();
			if(!StringUtil.isEmpty(user)&&!StringUtil.isEmpty(password))
				command.setCredentialsProvider(new UsernamePasswordCredentialsProvider( user, password ));
			command.call();
		} catch (IOException | GitAPIException e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(git!=null)
				git.close();
		}
		return true;
	}
	
	public static boolean pull(String repository, String lable) {
		return pull( repository,  lable,null,null);
	}
	
	public static boolean hardReset(String repository, String commitId){
		Git git = null;
		try {
			git = Git.open(FileUtil.checkOrmkdirs(repository));
			git.reset().setMode(ResetType.HARD).setRef(commitId);
			git.close();
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}finally{
			git.close();
		}
		return false;
		

	}

	public static Iterable<RevCommit> log(String repository, int maxCount) {
		Git git = null;
		try {
			git = Git.open(FileUtil.checkOrmkdirs(repository));
			LogCommand logCommand = git.log();
			if (maxCount > 0)
				logCommand.setMaxCount(maxCount);
			return logCommand.call();

		} catch (IOException | GitAPIException e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(git!=null)
				git.close();
		}
		return null;
	}
	
	public static String parseLocalResp(String remoteRepository){
		String repository = remoteRepository.substring(remoteRepository.lastIndexOf("/")+1,remoteRepository.lastIndexOf("."));
		return (System.getProperty("user.home")+"/"+repository);
	}
	
	public static void commitAndPush(String localRepository,String user,String password ){
		if(StringUtil.isEmpty(localRepository)){
			logger.error("localRepository Is Empty");
			System.exit(-1);
		}
		Git git = null;
		try {
			git = Git.open(FileUtil.checkOrmkdirs(localRepository));
			AddCommand add = git.add();
			add.addFilepattern(".").call();
	        CommitCommand commit = git.commit();  
	        commit.setCommitter("yin_cb", "yin_changbao@hoperun.com");  
	        commit.setAuthor("yin_cb","yin_changbao@hoperun.com");  
	        commit.setAll(true);
	        RevCommit revCommit = commit.setMessage("use jgit").call();  
	        String commitId = revCommit.getId().name();  
	        logger.info("Commit done, Commit Id {} ",commitId);
	        PushCommand push = git.push();
	        push.setCredentialsProvider(new UsernamePasswordCredentialsProvider( user, password ));
	        push.call();
	        
		} catch (IOException | GitAPIException e) {
			logger.error(e.getMessage(), e);
		}finally{
			if(git!=null)
				git.close();
		}
	}
	
	
	public static boolean hasUpdates(String localResp,String user,String passwd){
		Iterator<RevCommit> com = GitHelper.log(localResp,1).iterator();
		String oldCommitId = com.next().getId().getName();
		GitHelper.pull(localResp, null, user,passwd);
		Iterator<RevCommit> com1 = GitHelper.log(localResp,1).iterator();
		String newCommitId = com1.next().getId().getName();
		return !oldCommitId.equals(newCommitId);
	}
	
	
	public static void main(String args[]){
		String remoteRepository = "	http://yin_cb@10.10.10.1:19080/p/Common/Component/rule-workbanch-rules.git";
		String repository = remoteRepository.substring(remoteRepository.lastIndexOf("/")+1,remoteRepository.lastIndexOf("."));
		//GitHelper.clone("http://yin_cb@10.10.10.1:19080/p/Common/Component/rule-workbanch-rules.git", "D:/jgti/rr", "","yin_cb","ldap");
		
		System.out.println(hasUpdates("D:/jgti/rr", "yin_cb", "ldap"));
		//GitHelper.commitAndPush("D:/jgti/rr", "yin_cb", "ldap");
	}

}
