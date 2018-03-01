package com.jmm.drools.bean;

import java.util.List;

/**
 * 规则基础类
 * @author biris
 *
 */
public class BaseRule {
	
	//规则名称
	private String ruleName;
	
	//对应指标
	private List<Indicator> indicators;
	
	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
}
