package com.xiaocui.platform.model.data;

public class Xyvalidation {
    
    //序号
    private java.lang.String serno;
    
    //查询标识码
    private java.lang.String trxno;
    
    //借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
    private java.lang.String borrowtype;
    
    //借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
    private java.lang.String borrowstate;
    
    //借款金额
    private java.lang.String borrowamount;
    
    //合同日期
    private java.util.Date contractdate;
    
    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
    private java.lang.String repaystate;
    
    //批贷期数
    private java.lang.String loanperiod;
    
    //欠款金额
    private java.lang.String arrearsamount;
    
    //公司代码
    private java.lang.String companycode;
    
    
    /**
	 * @description 功能描述：获取序号
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getSerno() {
        return serno;
    }

    /**
	 * @description 功能描述：设置序号
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param serno
	 * @return 返回参数： void
	 */
    public void setSerno(java.lang.String serno) {
        this.serno = serno;
    }
    
    /**
	 * @description 功能描述：获取查询标识码
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getTrxno() {
        return trxno;
    }

    /**
	 * @description 功能描述：设置查询标识码
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param trxno
	 * @return 返回参数： void
	 */
    public void setTrxno(java.lang.String trxno) {
        this.trxno = trxno;
    }
    
    /**
	 * @description 功能描述：获取借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getBorrowtype() {
        return borrowtype;
    }

    /**
	 * @description 功能描述：设置借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param borrowtype
	 * @return 返回参数： void
	 */
    public void setBorrowtype(java.lang.String borrowtype) {
        this.borrowtype = borrowtype;
    }
    
    /**
	 * @description 功能描述：获取借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getBorrowstate() {
        return borrowstate;
    }

    /**
	 * @description 功能描述：设置借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param borrowstate
	 * @return 返回参数： void
	 */
    public void setBorrowstate(java.lang.String borrowstate) {
        this.borrowstate = borrowstate;
    }
    
    /**
	 * @description 功能描述：获取借款金额
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getBorrowamount() {
        return borrowamount;
    }

    /**
	 * @description 功能描述：设置借款金额
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param borrowamount
	 * @return 返回参数： void
	 */
    public void setBorrowamount(java.lang.String borrowamount) {
        this.borrowamount = borrowamount;
    }
    
    /**
	 * @description 功能描述：获取合同日期
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.util.Date
	 */
    public java.util.Date getContractdate() {
        return contractdate;
    }

    /**
	 * @description 功能描述：设置合同日期
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param contractdate
	 * @return 返回参数： void
	 */
    public void setContractdate(java.util.Date contractdate) {
        this.contractdate = contractdate;
    }
    
    /**
	 * @description 功能描述：获取还款状态 0.未知1.正常2.m1 3.m2 4.m3 5.m4 6.m5 7.m6 8.m6+
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getRepaystate() {
        return repaystate;
    }

    /**
	 * @description 功能描述：设置还款状态 0.未知1.正常2.m1 3.m2 4.m3 5.m4 6.m5 7.m6 8.m6+
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param repaystate
	 * @return 返回参数： void
	 */
    public void setRepaystate(java.lang.String repaystate) {
        this.repaystate = repaystate;
    }
    
    /**
	 * @description 功能描述：获取批贷期数
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getLoanperiod() {
        return loanperiod;
    }

    /**
	 * @description 功能描述：设置批贷期数
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param loanperiod
	 * @return 返回参数： void
	 */
    public void setLoanperiod(java.lang.String loanperiod) {
        this.loanperiod = loanperiod;
    }
    
    /**
	 * @description 功能描述：获取欠款金额
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getArrearsamount() {
        return arrearsamount;
    }

    /**
	 * @description 功能描述：设置欠款金额
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param arrearsamount
	 * @return 返回参数： void
	 */
    public void setArrearsamount(java.lang.String arrearsamount) {
        this.arrearsamount = arrearsamount;
    }
    
    /**
	 * @description 功能描述：获取公司代码
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getCompanycode() {
        return companycode;
    }

    /**
	 * @description 功能描述：设置公司代码
	 * @createdate 创建日期 2015-10-28 11:01:47
	 * @params 传入参数：@param companycode
	 * @return 返回参数： void
	 */
    public void setCompanycode(java.lang.String companycode) {
        this.companycode = companycode;
    }
    
    
    public Xyvalidation()
    {
    }
}
