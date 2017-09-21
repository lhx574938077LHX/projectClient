package com.xiaocui.common;

import java.io.Serializable;

public class Pkg1002 implements Serializable {
	private static final long serialVersionUID = 7422466979283592504L;
	
	private String trxNo;	//查询编号

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}
	
}

