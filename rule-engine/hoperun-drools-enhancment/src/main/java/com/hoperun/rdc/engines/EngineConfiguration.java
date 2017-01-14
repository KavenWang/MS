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
package com.hoperun.rdc.engines;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoperun.rdc.utils.FileUtil;
import com.hoperun.rdc.utils.GitHelper;
import com.hoperun.rdc.utils.StringUtil;

/**
 * ClassName: EngineConfiguration <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: Oct 24, 2016 9:33:22 PM <br/>
 *
 * @author yin_changbao
 * @version
 */
public class EngineConfiguration implements java.io.Serializable {

	private static final Logger logger = LoggerFactory.getLogger(EngineConfiguration.class);


	private static final long serialVersionUID = 1L;

	String ruleBase;

	String lable;

	String indicator;

	String versionId;

	boolean useGitAsRepository = true;

	List<File> files = new ArrayList<File>();



	private EngineConfiguration(){}

	/**
	 * Creates a new instance of EngineConfiguration.
	 */
	public EngineConfiguration(String ruleBase, String lable, String indicator, String versionId) {
		super();
		this.ruleBase = ruleBase;
		this.lable = lable;
		this.indicator = indicator;
		this.versionId = versionId;
		if(useGitAsRepository)
			this.files = FileUtil.iterateFolder(GitHelper.parseLocalResp(this.ruleBase) + File.separator + this.indicator, null);
		else
			this.files = FileUtil.iterateFolder(this.ruleBase + File.separator + this.indicator, null);
	}

	/**
	 * Creates a new instance of EngineConfiguration.
	 */
	public EngineConfiguration(String ruleBase, String lable, String indicator, String versionId,
			boolean useGitAsRepository) {
		super();
		this.ruleBase = ruleBase;
		this.lable = lable;
		this.indicator = indicator;
		this.versionId = versionId;
		this.useGitAsRepository = useGitAsRepository;
		if(useGitAsRepository)
			this.files = FileUtil.iterateFolder(GitHelper.parseLocalResp(this.ruleBase) + File.separator + this.indicator, null);
		else
			this.files = FileUtil.iterateFolder(this.ruleBase + File.separator + this.indicator, null);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + StringUtil.nullToEmpty(ruleBase).hashCode();
		result = 31 * result + StringUtil.nullToEmpty(lable).hashCode();
		result = 31 * result + StringUtil.nullToEmpty(indicator).hashCode();
		result = 31 * result + StringUtil.nullToEmpty(versionId).hashCode();
		if(this.files!=null&&!this.files.isEmpty()){
			result = 31 * result + files.hashCode();
			try {
				result += fileHashCode();
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(),e);
			}
		}

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (this.hashCode() != obj.hashCode()) {
			return false;
		}
		if (obj instanceof EngineConfiguration) {
			EngineConfiguration tmp = (EngineConfiguration) obj;
			if (tmp.ruleBase == this.ruleBase && tmp.lable == this.lable && tmp.indicator == this.indicator
					&& tmp.versionId == this.versionId)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "EngineConfiguration [ruleBase=" + ruleBase + ", lable=" + lable + ", indicator=" + indicator
				+ ", versionId=" + versionId + "]";
	}


	private int fileHashCode( ) throws FileNotFoundException{
		int hcode = 0;
		for(File file:files)
			if(file.exists())
				hcode =31 * hcode + FileUtil.getMd5Value(file).hashCode();
		return hcode;

	}

}
