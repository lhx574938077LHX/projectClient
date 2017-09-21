package com.xiaocui.common;

import java.io.Serializable;

public class Pkg3001 implements Serializable {
	private static final long serialVersionUID = 9060593078090073353L;
	
	private String realName;	//姓名
	private String idCard;		//身份证号
	private String companyCode;	//公司代码

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}

