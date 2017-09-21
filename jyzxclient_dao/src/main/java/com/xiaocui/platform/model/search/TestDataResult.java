package com.xiaocui.platform.model.search;

public class TestDataResult {
    
    //标识列
    private java.lang.Long id;
    
    //查询编码
    private java.lang.String trxNo;
    
    //用户姓名
    private java.lang.String realName;
    
    //身份证号
    private java.lang.String idcard;
    
    //公司编码
    private java.lang.String companyCode;
    
    //借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
    private java.lang.Short borrowtYpe;
    
    //借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
    private java.lang.Short borrowState;
    
    //借款金额 -7.[0,0.1) -6.[0.1,0.2) -5.[0.2,0.3) -4.[0.3,0.4) -3.[0.4,0.6) -2.[0.6,0.8) -1.[0.8,1) 1.[1,2) 2.[2,4) 3.[4,6) 4.[6,8)…….
    private java.lang.Short borrowAmount;
    
    //合同日期
    private java.lang.Long contractDate;
    
    //批贷期数
    private java.lang.Short loanPeriod;
    
    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
    private java.lang.Short repayState;
    
    //欠款金额
    private java.lang.Long arrearsAmount;
    
    //记录创建时间
    private java.lang.Long recTime;
    
    
    /**
	 * @description 功能描述：获取标识列
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getId() {
        return id;
    }

    /**
	 * @description 功能描述：设置标识列
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param id
	 * @return 返回参数： void
	 */
    public void setId(java.lang.Long id) {
        this.id = id;
    }
    
    /**
	 * @description 功能描述：获取查询编码
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getTrxNo() {
        return trxNo;
    }

    /**
	 * @description 功能描述：设置查询编码
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param trxNo
	 * @return 返回参数： void
	 */
    public void setTrxNo(java.lang.String trxNo) {
        this.trxNo = trxNo;
    }
    
    /**
	 * @description 功能描述：获取用户姓名
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getRealName() {
        return realName;
    }

    /**
	 * @description 功能描述：设置用户姓名
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param realName
	 * @return 返回参数： void
	 */
    public void setRealName(java.lang.String realName) {
        this.realName = realName;
    }
    
    /**
	 * @description 功能描述：获取身份证号
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getIdcard() {
        return idcard;
    }

    /**
	 * @description 功能描述：设置身份证号
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param idcard
	 * @return 返回参数： void
	 */
    public void setIdcard(java.lang.String idcard) {
        this.idcard = idcard;
    }
    
    /**
	 * @description 功能描述：获取公司编码
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getCompanyCode() {
        return companyCode;
    }

    /**
	 * @description 功能描述：设置公司编码
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param companyCode
	 * @return 返回参数： void
	 */
    public void setCompanyCode(java.lang.String companyCode) {
        this.companyCode = companyCode;
    }
    
    /**
	 * @description 功能描述：获取借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getBorrowtYpe() {
        return borrowtYpe;
    }

    /**
	 * @description 功能描述：设置借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param borrowtYpe
	 * @return 返回参数： void
	 */
    public void setBorrowtYpe(java.lang.Short borrowtYpe) {
        this.borrowtYpe = borrowtYpe;
    }
    
    /**
	 * @description 功能描述：获取借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getBorrowState() {
        return borrowState;
    }

    /**
	 * @description 功能描述：设置借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param borrowState
	 * @return 返回参数： void
	 */
    public void setBorrowState(java.lang.Short borrowState) {
        this.borrowState = borrowState;
    }
    
    /**
	 * @description 功能描述：获取借款金额 -7.[0,0.1) -6.[0.1,0.2) -5.[0.2,0.3) -4.[0.3,0.4) -3.[0.4,0.6) -2.[0.6,0.8) -1.[0.8,1) 1.[1,2) 2.[2,4) 3.[4,6) 4.[6,8)…….
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getBorrowAmount() {
        return borrowAmount;
    }

    /**
	 * @description 功能描述：设置借款金额 -7.[0,0.1) -6.[0.1,0.2) -5.[0.2,0.3) -4.[0.3,0.4) -3.[0.4,0.6) -2.[0.6,0.8) -1.[0.8,1) 1.[1,2) 2.[2,4) 3.[4,6) 4.[6,8)…….
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param borrowAmount
	 * @return 返回参数： void
	 */
    public void setBorrowAmount(java.lang.Short borrowAmount) {
        this.borrowAmount = borrowAmount;
    }
    
    /**
	 * @description 功能描述：获取合同日期
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getContractDate() {
        return contractDate;
    }

    /**
	 * @description 功能描述：设置合同日期
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param contractDate
	 * @return 返回参数： void
	 */
    public void setContractDate(java.lang.Long contractDate) {
        this.contractDate = contractDate;
    }
    
    /**
	 * @description 功能描述：获取批贷期数
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getLoanPeriod() {
        return loanPeriod;
    }

    /**
	 * @description 功能描述：设置批贷期数
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param loanPeriod
	 * @return 返回参数： void
	 */
    public void setLoanPeriod(java.lang.Short loanPeriod) {
        this.loanPeriod = loanPeriod;
    }
    
    /**
	 * @description 功能描述：获取还款状态 0.未知1.正常2.m1 3.m2 4.m3 5.m4 6.m5 7.m6 8.m6+
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getRepayState() {
        return repayState;
    }

    /**
	 * @description 功能描述：设置还款状态 0.未知1.正常2.m1 3.m2 4.m3 5.m4 6.m5 7.m6 8.m6+
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param repayState
	 * @return 返回参数： void
	 */
    public void setRepayState(java.lang.Short repayState) {
        this.repayState = repayState;
    }
    
    /**
	 * @description 功能描述：获取欠款金额
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getArrearsAmount() {
        return arrearsAmount;
    }

    /**
	 * @description 功能描述：设置欠款金额
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param arrearsAmount
	 * @return 返回参数： void
	 */
    public void setArrearsAmount(java.lang.Long arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
    }
    
    /**
	 * @description 功能描述：获取记录创建时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getRecTime() {
        return recTime;
    }

    /**
	 * @description 功能描述：设置记录创建时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param recTime
	 * @return 返回参数： void
	 */
    public void setRecTime(java.lang.Long recTime) {
        this.recTime = recTime;
    }
    
    
    public TestDataResult()
    {
    }
}
