package com.xiaocui.common;

import java.io.Serializable;
import java.util.List;

public class Pkg4001 implements Serializable {
	private static final long serialVersionUID = -7677544277150607151L;
	
	private List<LoanInfo> loanInfos;
	
	public List<LoanInfo> getLoanInfos() {
		return loanInfos;
	}

	public void setLoanInfos(List<LoanInfo> loanInfos) {
		this.loanInfos = loanInfos;
	}
}

