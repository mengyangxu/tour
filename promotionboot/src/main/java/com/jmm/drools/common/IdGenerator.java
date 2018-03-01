package com.jmm.drools.common;

interface IdGenerator {

	/**
	 * 获取ID
	 * @return id值
	 */
	public long nextId();
}