package com.xiaocui.platform.model.search;

public class SearchLog {
    
    //标识列
    private java.lang.Long id;
    
    //查询流水号
    private java.lang.String searchTrxNo;
    
    //姓名
    private java.lang.String realName;
    
    //身份证号
    private java.lang.String idCard;
    
    //请求公司ID
    private java.lang.Long reqCompanyId;
    
    //公司编码
    private java.lang.String reqCompanyCode;
    
    //异步状态 0.未知1.待查询 2.查询中 3.待反馈结果 4.反馈次数上限 5.回调上报已完成6.非回调上报完成
    private java.lang.Short state;
    
    //同步状态 0.未知1.查询中 2.已完成3.需重复报送
    private java.lang.Short syncState;
    
    //查询类型 0.旧异步接口查询1.网站查询2.网站批量查询3.快速查询接口4.新异步接口查询
    private java.lang.Short searchType;
    
    //创建时间
    private java.lang.Long recTime;
    
    //完成时间
    private java.lang.Long endTime;
    
    
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
	 * @description 功能描述：获取姓名
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getRealName() {
        return realName;
    }

    /**
	 * @description 功能描述：设置姓名
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param realName
	 * @return 返回参数： void
	 */
    public void setRealName(java.lang.String realName) {
        this.realName = realName;
    }
    
    /**
	 * @description 功能描述：获取身份证号
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getIdCard() {
        return idCard;
    }

    /**
	 * @description 功能描述：设置身份证号
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param idCard
	 * @return 返回参数： void
	 */
    public void setIdCard(java.lang.String idCard) {
        this.idCard = idCard;
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
	 * @description 功能描述：获取公司编码
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getReqCompanyCode() {
        return reqCompanyCode;
    }

    /**
	 * @description 功能描述：设置公司编码
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param reqCompanyCode
	 * @return 返回参数： void
	 */
    public void setReqCompanyCode(java.lang.String reqCompanyCode) {
        this.reqCompanyCode = reqCompanyCode;
    }
    
    /**
	 * @description 功能描述：获取异步状态 0.未知1.待查询 2.查询中 3.待反馈结果 4.反馈次数上限 5.回调上报已完成6.非回调上报完成
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getState() {
        return state;
    }

    /**
	 * @description 功能描述：设置异步状态 0.未知1.待查询 2.查询中 3.待反馈结果 4.反馈次数上限 5.回调上报已完成6.非回调上报完成
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param state
	 * @return 返回参数： void
	 */
    public void setState(java.lang.Short state) {
        this.state = state;
    }
    
    /**
	 * @description 功能描述：获取同步状态 0.未知1.查询中 2.已完成3.需重复报送
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getSyncState() {
        return syncState;
    }

    /**
	 * @description 功能描述：设置同步状态 0.未知1.查询中 2.已完成3.需重复报送
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param syncState
	 * @return 返回参数： void
	 */
    public void setSyncState(java.lang.Short syncState) {
        this.syncState = syncState;
    }
    
    /**
	 * @description 功能描述：获取查询类型 0.旧异步接口查询1.网站查询2.网站批量查询3.快速查询接口4.新异步接口查询
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getSearchType() {
        return searchType;
    }

    /**
	 * @description 功能描述：设置查询类型 0.旧异步接口查询1.网站查询2.网站批量查询3.快速查询接口4.新异步接口查询
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param searchType
	 * @return 返回参数： void
	 */
    public void setSearchType(java.lang.Short searchType) {
        this.searchType = searchType;
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
	 * @description 功能描述：获取完成时间
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getEndTime() {
        return endTime;
    }

    /**
	 * @description 功能描述：设置完成时间
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param endTime
	 * @return 返回参数： void
	 */
    public void setEndTime(java.lang.Long endTime) {
        this.endTime = endTime;
    }
    
    
    public SearchLog()
    {
    }
}
