package com.xiaocui.entity;

public class PkgSecurity {
	
	private String data;
	private String companyCode; 
	private String sign;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String toString(){
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(this.data+"|")
			.append(this.companyCode+"|")
			.append(this.sign);
		return strBuf.toString();
		
	}
	
}
