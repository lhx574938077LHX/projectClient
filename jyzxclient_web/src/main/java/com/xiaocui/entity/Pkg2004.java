package com.xiaocui.entity;

import java.io.Serializable;
import java.util.List;

public class Pkg2004 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5878439265820975210L;
	//剩余积分
	private Double integralNum;
	//充值记录
	private List<Recharge> recharges;
	//本月及历史账单
	private List<Bill> bills;
	

	public Double getIntegralNum() {
		return integralNum;
	}
	public void setIntegralNum(Double integralNum) {
		this.integralNum = integralNum;
	}
	public List<Recharge> getRecharges() {
		return recharges;
	}
	public void setRecharges(List<Recharge> recharges) {
		this.recharges = recharges;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
