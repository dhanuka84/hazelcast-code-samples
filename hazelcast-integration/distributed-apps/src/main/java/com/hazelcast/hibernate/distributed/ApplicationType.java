package com.hazelcast.hibernate.distributed;

import java.util.HashMap;
import java.util.Map;

public enum ApplicationType {
	
	SYNTHETIC_COLLECTOR;
	
	private static final Map<String,ApplicationType> typeMap = new HashMap<String,ApplicationType>();
	
	static {
		for(ApplicationType type : ApplicationType.values()){
			typeMap.put(type.toString(), type);
		}
	}
	
	public static ApplicationType getApplicationType(final String name){
		return typeMap.get(name);
	}

}
