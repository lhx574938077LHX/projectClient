package com.xiaocui.common;

import java.io.Serializable;

public class Pkg2001 implements Serializable {
	private static final long serialVersionUID = 2844221639108684359L;
	
	private String trxNo;	//GUID
	
	public String getTrxNo() {
		return trxNo;
	}
	
	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}
}

