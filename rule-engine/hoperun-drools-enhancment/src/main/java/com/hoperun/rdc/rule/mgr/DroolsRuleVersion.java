package com.hoperun.rdc.rule.mgr;

import org.eclipse.jgit.revwalk.RevCommit;

public class DroolsRuleVersion implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6926691848270129456L;

	private String versionId;
	
	private long commitDate;
	
	private String auther;
	
	private String comments;
	
	
	public DroolsRuleVersion(RevCommit rev){
		if(rev!=null){
			this.auther = rev.getCommitterIdent().getName();
			this.versionId = rev.getId().name();
			this.commitDate=rev.getCommitTime()*1000L;
			this.comments = rev.getFullMessage();
		}
		
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}



	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "DroolsRuleVersion [versionId=" + versionId + ", commitDateStr=" + commitDate + ", auther=" + auther
				+ ", comments=" + comments + "]";
	}
	
	
}
