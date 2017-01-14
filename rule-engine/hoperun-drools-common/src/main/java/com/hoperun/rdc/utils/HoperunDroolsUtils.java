package com.hoperun.rdc.utils;

import java.util.Collection;

public class HoperunDroolsUtils {
	
	
	
	private HoperunDroolsUtils(){};
	
	public static  <T>  boolean isEmpty(Collection<T> collection){
		
		return collection==null||collection.isEmpty();
		
	}

}
