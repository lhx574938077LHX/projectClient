package com.xiaocui.platform.model.search;

public class JoinCondition {
    
    //标识列
    private java.lang.Long id;
    
    //请求公司ID
    private java.lang.Long companyId;
    
    //开始时间
    private java.lang.Long startTime;
    
    //结束时间
    private java.lang.Long endTime;
    
    //对接状态 0.建群发文1.等待开发2.对接联调3.等待上线4.系统上线5.放弃跟进
    private java.lang.Short joinState;
    
    //实时信息
    private java.lang.String timeInformation;
    
    //备注
    private java.lang.String remark;
    
    //客户经理
    private java.lang.String bdName;
    
    //客户邮箱
    private java.lang.String clientEmail;
    
    //最近更新时间
    private java.lang.Long updateTime;
    
    //是否开放被查询 0.不开放1.开放
    private java.lang.Short isP3001;
    
    //创建时间
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
	 * @description 功能描述：获取请求公司id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getCompanyId() {
        return companyId;
    }

    /**
	 * @description 功能描述：设置请求公司id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param companyId
	 * @return 返回参数： void
	 */
    public void setCompanyId(java.lang.Long companyId) {
        this.companyId = companyId;
    }
    
    /**
	 * @description 功能描述：获取开始时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getStartTime() {
        return startTime;
    }

    /**
	 * @description 功能描述：设置开始时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param startTime
	 * @return 返回参数： void
	 */
    public void setStartTime(java.lang.Long startTime) {
        this.startTime = startTime;
    }
    
    /**
	 * @description 功能描述：获取结束时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getEndTime() {
        return endTime;
    }

    /**
	 * @description 功能描述：设置结束时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param endTime
	 * @return 返回参数： void
	 */
    public void setEndTime(java.lang.Long endTime) {
        this.endTime = endTime;
    }
    
    /**
	 * @description 功能描述：获取对接状态 0.建群发文1.等待开发2.对接联调3.等待上线4.系统上线5.放弃跟进
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getJoinState() {
        return joinState;
    }

    /**
	 * @description 功能描述：设置对接状态 0.建群发文1.等待开发2.对接联调3.等待上线4.系统上线5.放弃跟进
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param joinState
	 * @return 返回参数： void
	 */
    public void setJoinState(java.lang.Short joinState) {
        this.joinState = joinState;
    }
    
    /**
	 * @description 功能描述：获取实时信息
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getTimeInformation() {
        return timeInformation;
    }

    /**
	 * @description 功能描述：设置实时信息
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param timeInformation
	 * @return 返回参数： void
	 */
    public void setTimeInformation(java.lang.String timeInformation) {
        this.timeInformation = timeInformation;
    }
    
    /**
	 * @description 功能描述：获取备注
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
	 * @description 功能描述：设置备注
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param remark
	 * @return 返回参数： void
	 */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }
    
    /**
	 * @description 功能描述：获取客户经理
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getBdName() {
        return bdName;
    }

    /**
	 * @description 功能描述：设置客户经理
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param bdName
	 * @return 返回参数： void
	 */
    public void setBdName(java.lang.String bdName) {
        this.bdName = bdName;
    }
    
    /**
	 * @description 功能描述：获取客户邮箱
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getClientEmail() {
        return clientEmail;
    }

    /**
	 * @description 功能描述：设置客户邮箱
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param clientEmail
	 * @return 返回参数： void
	 */
    public void setClientEmail(java.lang.String clientEmail) {
        this.clientEmail = clientEmail;
    }
    
    /**
	 * @description 功能描述：获取最近更新时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getUpdateTime() {
        return updateTime;
    }

    /**
	 * @description 功能描述：设置最近更新时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param updateTime
	 * @return 返回参数： void
	 */
    public void setUpdateTime(java.lang.Long updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
	 * @description 功能描述：获取是否开放被查询 0.不开放1.开放
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getIsP3001() {
        return isP3001;
    }

    /**
	 * @description 功能描述：设置是否开放被查询 0.不开放1.开放
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param isP3001
	 * @return 返回参数： void
	 */
    public void setIsP3001(java.lang.Short isP3001) {
        this.isP3001 = isP3001;
    }
    
    /**
	 * @description 功能描述：获取创建时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getRecTime() {
        return recTime;
    }

    /**
	 * @description 功能描述：设置创建时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param recTime
	 * @return 返回参数： void
	 */
    public void setRecTime(java.lang.Long recTime) {
        this.recTime = recTime;
    }
    
    
    public JoinCondition()
    {
    }
}
