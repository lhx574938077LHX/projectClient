package com.xiaocui.platform.model.search;

public class SearchResult {
    
    //标识列
    private java.lang.Long id;
    
    //查询ID
    private java.lang.Long searchLogId;
    
    //查询流水号
    private java.lang.String searchTrxNo;
    
    //请求公司ID
    private java.lang.Long reqCompanyId;
    
    //请求公司编码
    private java.lang.String reqCompanyCode;
    
    //响应公司ID
    private java.lang.Long rspCompanyId;
    
    //响应公司编码
    private java.lang.String rspCompanyCode;
    
    //借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
    private java.lang.Short borrowType;
    
    //借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
    private java.lang.Short borrowState;
    
    //借款金额 0.未知 1.[0,2], 2.[2,4], 3.[4,6], 4.[6,8] ........
    private java.lang.Short borrowAmount;
    
    //合同日期
    private java.lang.Long contractDate;
    
    //批贷期数
    private java.lang.Short loanPeriod;
    
    //还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清
    private java.lang.Short repayState;
    
    //欠款金额
    private java.lang.Long arrearsAmount;
    
    //创建时间
    private java.lang.Long recTime;
    
    //记录类型 0.常规记录1.快速查询记录同步2.快速查询记录异步
    private java.lang.Short resultType;
    
    //记录类型 0.常规记录1.JD_DATA2.SL_DATA
    private java.lang.Short dataType;
    
    
    /**
	 * @description 功能描述：获取标识列
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getId() {
        return id;
    }

    /**
	 * @description 功能描述：设置标识列
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param id
	 * @return 返回参数： void
	 */
    public void setId(java.lang.Long id) {
        this.id = id;
    }
    
    /**
	 * @description 功能描述：获取查询id
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getSearchLogId() {
        return searchLogId;
    }

    /**
	 * @description 功能描述：设置查询id
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param searchLogId
	 * @return 返回参数： void
	 */
    public void setSearchLogId(java.lang.Long searchLogId) {
        this.searchLogId = searchLogId;
    }
    
    /**
	 * @description 功能描述：获取查询流水号
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getSearchTrxNo() {
        return searchTrxNo;
    }

    /**
	 * @description 功能描述：设置查询流水号
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param searchTrxNo
	 * @return 返回参数： void
	 */
    public void setSearchTrxNo(java.lang.String searchTrxNo) {
        this.searchTrxNo = searchTrxNo;
    }
    
    /**
	 * @description 功能描述：获取请求公司id
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getReqCompanyId() {
        return reqCompanyId;
    }

    /**
	 * @description 功能描述：设置请求公司id
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param reqCompanyId
	 * @return 返回参数： void
	 */
    public void setReqCompanyId(java.lang.Long reqCompanyId) {
        this.reqCompanyId = reqCompanyId;
    }
    
    /**
	 * @description 功能描述：获取请求公司编码
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getReqCompanyCode() {
        return reqCompanyCode;
    }

    /**
	 * @description 功能描述：设置请求公司编码
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param reqCompanyCode
	 * @return 返回参数： void
	 */
    public void setReqCompanyCode(java.lang.String reqCompanyCode) {
        this.reqCompanyCode = reqCompanyCode;
    }
    
    /**
	 * @description 功能描述：获取响应公司id
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getRspCompanyId() {
        return rspCompanyId;
    }

    /**
	 * @description 功能描述：设置响应公司id
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param rspCompanyId
	 * @return 返回参数： void
	 */
    public void setRspCompanyId(java.lang.Long rspCompanyId) {
        this.rspCompanyId = rspCompanyId;
    }
    
    /**
	 * @description 功能描述：获取响应公司编码
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getRspCompanyCode() {
        return rspCompanyCode;
    }

    /**
	 * @description 功能描述：设置响应公司编码
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param rspCompanyCode
	 * @return 返回参数： void
	 */
    public void setRspCompanyCode(java.lang.String rspCompanyCode) {
        this.rspCompanyCode = rspCompanyCode;
    }
    
    /**
	 * @description 功能描述：获取借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getBorrowType() {
        return borrowType;
    }

    /**
	 * @description 功能描述：设置借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param borrowType
	 * @return 返回参数： void
	 */
    public void setBorrowType(java.lang.Short borrowType) {
        this.borrowType = borrowType;
    }
    
    /**
	 * @description 功能描述：获取借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getBorrowState() {
        return borrowState;
    }

    /**
	 * @description 功能描述：设置借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param borrowState
	 * @return 返回参数： void
	 */
    public void setBorrowState(java.lang.Short borrowState) {
        this.borrowState = borrowState;
    }
    
    /**
	 * @description 功能描述：获取借款金额 0.未知 1.[0,2], 2.[2,4], 3.[4,6], 4.[6,8] ........
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getBorrowAmount() {
        return borrowAmount;
    }

    /**
	 * @description 功能描述：设置借款金额 0.未知 1.[0,2], 2.[2,4], 3.[4,6], 4.[6,8] ........
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param borrowAmount
	 * @return 返回参数： void
	 */
    public void setBorrowAmount(java.lang.Short borrowAmount) {
        this.borrowAmount = borrowAmount;
    }
    
    /**
	 * @description 功能描述：获取合同日期
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getContractDate() {
        return contractDate;
    }

    /**
	 * @description 功能描述：设置合同日期
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param contractDate
	 * @return 返回参数： void
	 */
    public void setContractDate(java.lang.Long contractDate) {
        this.contractDate = contractDate;
    }
    
    /**
	 * @description 功能描述：获取批贷期数
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getLoanPeriod() {
        return loanPeriod;
    }

    /**
	 * @description 功能描述：设置批贷期数
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param loanPeriod
	 * @return 返回参数： void
	 */
    public void setLoanPeriod(java.lang.Short loanPeriod) {
        this.loanPeriod = loanPeriod;
    }
    
    /**
	 * @description 功能描述：获取还款状态 0.未知1.正常2.m1 3.m2 4.m3 5.m4 6.m5 7.m6 8.m6+ 9.已还清
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getRepayState() {
        return repayState;
    }

    /**
	 * @description 功能描述：设置还款状态 0.未知1.正常2.m1 3.m2 4.m3 5.m4 6.m5 7.m6 8.m6+ 9.已还清
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param repayState
	 * @return 返回参数： void
	 */
    public void setRepayState(java.lang.Short repayState) {
        this.repayState = repayState;
    }
    
    /**
	 * @description 功能描述：获取欠款金额
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getArrearsAmount() {
        return arrearsAmount;
    }

    /**
	 * @description 功能描述：设置欠款金额
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param arrearsAmount
	 * @return 返回参数： void
	 */
    public void setArrearsAmount(java.lang.Long arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
    }
    
    /**
	 * @description 功能描述：获取创建时间
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getRecTime() {
        return recTime;
    }

    /**
	 * @description 功能描述：设置创建时间
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param recTime
	 * @return 返回参数： void
	 */
    public void setRecTime(java.lang.Long recTime) {
        this.recTime = recTime;
    }
    
    /**
	 * @description 功能描述：获取记录类型 0.常规记录1.快速查询记录同步2.快速查询记录异步
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getResultType() {
        return resultType;
    }

    /**
	 * @description 功能描述：设置记录类型 0.常规记录1.快速查询记录同步2.快速查询记录异步
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param resultType
	 * @return 返回参数： void
	 */
    public void setResultType(java.lang.Short resultType) {
        this.resultType = resultType;
    }
    
    /**
	 * @description 功能描述：获取记录类型 0.常规记录1.jdData2.slData
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getDataType() {
        return dataType;
    }

    /**
	 * @description 功能描述：设置记录类型 0.常规记录1.jdData2.slData
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param dataType
	 * @return 返回参数： void
	 */
    public void setDataType(java.lang.Short dataType) {
        this.dataType = dataType;
    }
    
    
    public SearchResult()
    {
    }
}
