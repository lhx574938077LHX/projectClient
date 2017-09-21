package com.xiaocui.common;

import java.io.Serializable;
import java.util.List;

public class Pkg3002 implements Serializable {
	private static final long serialVersionUID = -8407131917130708552L;
	
	private String trxNo;
	private List<LoanInfo> loanInfos;

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}
	
	public List<LoanInfo> getLoanInfos() {
		return loanInfos;
	}

	public void setLoanInfos(List<LoanInfo> loanInfos) {
		this.loanInfos = loanInfos;
	}
}

