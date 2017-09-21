package com.xiaocui.common;

import java.io.Serializable;
import java.util.List;

public class Pkg2003 implements Serializable {
	private static final long serialVersionUID = -8600733661816766371L;
	
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

