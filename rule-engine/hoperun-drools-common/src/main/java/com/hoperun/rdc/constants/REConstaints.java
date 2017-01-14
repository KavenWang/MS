package com.hoperun.rdc.constants;

/**
 * 
* @ClassName: REConstaints.java 
* @Description: 
* @author YinChang-bao
* @date Nov 16, 2015
*
 */
public interface REConstaints {

	
	public static final String DROOLS_RULE_SUFIX_DRL= ".drl";
	public static final String DROOLS_RULE_SUFIX_TEMPLATE= ".template";
	public static final String DROOLS_RULE_SUFIX_RDRL= ".rdrl";
	public static final String DROOLS_RULE_SUFIX_RDSLR= ".rdslr";
	
	
	
	/************cache keys*******************/
	public static  final String CACHE_VECHIEL_ACCOUNT_VIERW 		=	"CACHE_VECHIEL_ACCOUNT_VIERW";
	public static  final String CACHE_COMPANY_ACCTOUNT_VIEW	 		=	"CACHE_COMPANY_ACCTOUNT_VIEW";
	public static  final String CACHE_FIRST_DRIVEN_VIEW_MAP 		=	"CACHE_FIRST_DRIVEN_VIEW_MAP";
	public static  final String CACHE_ACCIDENT_RISK_VIEW	 		=	"CACHE_ACCIDENT_RISK_VIEW";
	public static  final String CACHE_STOLEN_RISK_VIEW	 	    	=	"CACHE_STOLEN_RISK_VIEW";
	public static  final String CACHE_ABS_START_TIMES_EVERY_TRIP	=	"CACHE_ABS_START_TIMES_EVERY_TRIP";
	public static  final String CACHE_ABS_END_TIMES_EVERY_TRIP  	=	"CACHE_ABS_END_TIMES_EVERY_TRIP";
	public static  final String CACHE_ABS_VEHICLE_TOTAL_DRIVEN_TIME =	"CACHE_ABS_VEHICLE_TOTAL_DRIVEN_TIME";
	
	public static  final String CACHE_ABS_VEHICLE_IN_STOLEN_RADUIE =	"CACHE_ABS_VEHICLE_IN_STOLEN_RADUIE";
	
	public static  final String CACHE_ABS_VEHICLE_IN_RISK_RADUIE =	"CACHE_ABS_VEHICLE_IN_RISK_RADUIE_";

	
	
	
	/***********cache keys end****************/
	
	
	/*********   persistence name   *********/
	public static final String STATISTIC_DAILY_SCORE = "statisticDailyScoreDao";
	public static final String STATISTIC_MONTHLY_SCORE = "statisticMonthlyScoreDao";
	public static final String STATISTIC_SCENARIO_SCORE = "statisticScenarioScoreDataDao";
	
	/*********   persistence end    *********/
	
	/*********   drools rule file path   *********/
	public static final String AGGRESSIVEDRIVING_RULE_PATH = "c4d.ism.drools.fs.base.aggressiveDriving";
	public static final String VOLUMEOFDRIVING_RULE_PATH = "c4d.ism.drools.fs.base.volumeDriving";
	public static final String RISKAREA_RULE_PATH = "c4d.ism.drools.fs.base.riskArea";
	public static final String OVERALLSCORE_RULE_PATH = "c4d.ism.drools.fs.base.overall";
	
	/*********   drools rule file path end   *********/
	
	
	
	public static final int TIMEOUT = 30*60 * 1000;
	public static final int CONNECT_TIMEOUT = 15*60*1000;
	public static final int SOCKET_TIMEOUT = 30*60*1000;
	public static final String HTTP_HEAD_CONTENT_TYPE = "Content-Type";
	public static final String HTTP_HEAD_CONTENT_TYPE_VALUE = "application/json; charset=utf-8";
	
	
	public static final int LOCK_FLAG_IN_RDBMS = 0;
	public static final int LOCK_FLAG_IN_KVDB  = 1;
	public static final int LOCK_FLAG_IN_NOSQL = 2;
	
}
