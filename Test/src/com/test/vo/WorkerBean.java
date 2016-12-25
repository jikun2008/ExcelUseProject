package com.test.vo;

import java.math.BigDecimal;

/**
 * 实际工时
 * @author Administrator
 *
 */
public class WorkerBean {
  private String name;
  private BigDecimal money;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public BigDecimal getMoney() {
	return money;
}
public void setMoney(BigDecimal money) {
	this.money = money;
}
@Override
public String toString() {
	return "WorkerBean [name=" + name + ", money=" + money + "]";
}
  
  
  
}
