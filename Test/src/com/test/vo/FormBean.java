package com.test.vo;

import java.math.BigDecimal;

public class FormBean {
	
	public String money;
	
	public BigDecimal workinghoursBigDecimal;//工时费
	public String peopleName;
	public BigDecimal getWorkinghoursBigDecimal() {
		return workinghoursBigDecimal;
	}
	public void setWorkinghoursBigDecimal(BigDecimal workinghoursBigDecimal) {
		this.workinghoursBigDecimal = workinghoursBigDecimal;
	}
	public String getPeopleName() {
		return peopleName;
	}
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "FormBean [money=" + money + ", workinghoursBigDecimal=" + workinghoursBigDecimal + ", peopleName="
				+ peopleName + "]";
	}
	
	
	
	
	
	
	

}
