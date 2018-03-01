package com.jmm.drools.common;


public class IdUtil {

	private static IdGenerator idGenerator = new SnowflakeIdGenerator(1);
	
	public static synchronized long getId() {
		return idGenerator.nextId();
	}
	
}
