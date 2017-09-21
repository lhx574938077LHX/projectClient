package com.xiaocui.platform.model.search;

public class CompanyInfo {
    
    //标识列
    private java.lang.Long id;
    
    //通信签名
    private java.lang.String connSign;
    
    //公司名
    private java.lang.String companyName;
    
    //公司编码
    private java.lang.String companyCode;
    
    //积分
    private java.lang.Long integral;
    
    //当前状态 0.未知1.待审批 2.未通过审核 3.已开通 4.已停用
    private java.lang.Short state;
    
    //服务地址
    private java.lang.String serviceUrl;
    
    //3001被查 当前状态 0.未开通1.已开通
    private java.lang.Short p00001;
    
    //3002报送 当前状态 0.未开通1.已开通
    private java.lang.Short p00002;
    
    //加密方式 0.未开通1.签名通信2.RSA加密
    private java.lang.Short p00003;
    
    //1003功能 0.未开通1.已开通
    private java.lang.Short p00004;
    
    //创建时间
    private java.lang.Long recTime;
    
    //数据源类型 0.公开1.私有
    private java.lang.Short sourceType;
    
    //要排除的公司CODE 用|号分割
    private java.lang.String excluded;
    
    //绑定IP 用|号分割
    private java.lang.String bindIp;
    
    //积分透支额度
    private java.lang.Long integralOverdraft;
    
    //开始计费时间
    private java.lang.Long startChargingTime;
    
    //黑名单产品功能 0.未开通1.已开通
    private java.lang.Short p00005;
    
    //黑名单产品版本0.简版1.【明细版1】 显示最新的催收记录2.【明细版2】显示逾期最高的催收记录3.【明细版3】显示所有的记录
    private java.lang.Short p00005Param;
    
    
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
	 * @description 功能描述：获取通信签名
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getConnSign() {
        return connSign;
    }

    /**
	 * @description 功能描述：设置通信签名
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param connSign
	 * @return 返回参数： void
	 */
    public void setConnSign(java.lang.String connSign) {
        this.connSign = connSign;
    }
    
    /**
	 * @description 功能描述：获取公司名
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getCompanyName() {
        return companyName;
    }

    /**
	 * @description 功能描述：设置公司名
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param companyName
	 * @return 返回参数： void
	 */
    public void setCompanyName(java.lang.String companyName) {
        this.companyName = companyName;
    }
    
    /**
	 * @description 功能描述：获取公司编码
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getCompanyCode() {
        return companyCode;
    }

    /**
	 * @description 功能描述：设置公司编码
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param companyCode
	 * @return 返回参数： void
	 */
    public void setCompanyCode(java.lang.String companyCode) {
        this.companyCode = companyCode;
    }
    
    /**
	 * @description 功能描述：获取积分
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getIntegral() {
        return integral;
    }

    /**
	 * @description 功能描述：设置积分
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param integral
	 * @return 返回参数： void
	 */
    public void setIntegral(java.lang.Long integral) {
        this.integral = integral;
    }
    
    /**
	 * @description 功能描述：获取当前状态 0.未知1.待审批 2.未通过审核 3.已开通 4.已停用
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getState() {
        return state;
    }

    /**
	 * @description 功能描述：设置当前状态 0.未知1.待审批 2.未通过审核 3.已开通 4.已停用
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param state
	 * @return 返回参数： void
	 */
    public void setState(java.lang.Short state) {
        this.state = state;
    }
    
    /**
	 * @description 功能描述：获取服务地址
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getServiceUrl() {
        return serviceUrl;
    }

    /**
	 * @description 功能描述：设置服务地址
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param serviceUrl
	 * @return 返回参数： void
	 */
    public void setServiceUrl(java.lang.String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
    
    /**
	 * @description 功能描述：获取3001被查 当前状态 0.未开通1.已开通
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00001() {
        return p00001;
    }

    /**
	 * @description 功能描述：设置3001被查 当前状态 0.未开通1.已开通
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param p00001
	 * @return 返回参数： void
	 */
    public void setP00001(java.lang.Short p00001) {
        this.p00001 = p00001;
    }
    
    /**
	 * @description 功能描述：获取3002报送 当前状态 0.未开通1.已开通
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00002() {
        return p00002;
    }

    /**
	 * @description 功能描述：设置3002报送 当前状态 0.未开通1.已开通
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param p00002
	 * @return 返回参数： void
	 */
    public void setP00002(java.lang.Short p00002) {
        this.p00002 = p00002;
    }
    
    /**
	 * @description 功能描述：获取加密方式 0.未开通1.签名通信2.rsa加密
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00003() {
        return p00003;
    }

    /**
	 * @description 功能描述：设置加密方式 0.未开通1.签名通信2.rsa加密
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param p00003
	 * @return 返回参数： void
	 */
    public void setP00003(java.lang.Short p00003) {
        this.p00003 = p00003;
    }
    
    /**
	 * @description 功能描述：获取1003功能 0.未开通1.已开通
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00004() {
        return p00004;
    }

    /**
	 * @description 功能描述：设置1003功能 0.未开通1.已开通
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param p00004
	 * @return 返回参数： void
	 */
    public void setP00004(java.lang.Short p00004) {
        this.p00004 = p00004;
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
	 * @description 功能描述：获取数据源类型 0.公开1.私有
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getSourceType() {
        return sourceType;
    }

    /**
	 * @description 功能描述：设置数据源类型 0.公开1.私有
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param sourceType
	 * @return 返回参数： void
	 */
    public void setSourceType(java.lang.Short sourceType) {
        this.sourceType = sourceType;
    }
    
    /**
	 * @description 功能描述：获取要排除的公司code 用|号分割
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getExcluded() {
        return excluded;
    }

    /**
	 * @description 功能描述：设置要排除的公司code 用|号分割
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param excluded
	 * @return 返回参数： void
	 */
    public void setExcluded(java.lang.String excluded) {
        this.excluded = excluded;
    }
    
    /**
	 * @description 功能描述：获取绑定ip 用|号分割
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getBindIp() {
        return bindIp;
    }

    /**
	 * @description 功能描述：设置绑定ip 用|号分割
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param bindIp
	 * @return 返回参数： void
	 */
    public void setBindIp(java.lang.String bindIp) {
        this.bindIp = bindIp;
    }
    
    /**
	 * @description 功能描述：获取积分透支额度
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getIntegralOverdraft() {
        return integralOverdraft;
    }

    /**
	 * @description 功能描述：设置积分透支额度
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param integralOverdraft
	 * @return 返回参数： void
	 */
    public void setIntegralOverdraft(java.lang.Long integralOverdraft) {
        this.integralOverdraft = integralOverdraft;
    }
    
    /**
	 * @description 功能描述：获取开始计费时间
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getStartChargingTime() {
        return startChargingTime;
    }

    /**
	 * @description 功能描述：设置开始计费时间
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param startChargingTime
	 * @return 返回参数： void
	 */
    public void setStartChargingTime(java.lang.Long startChargingTime) {
        this.startChargingTime = startChargingTime;
    }
    
    /**
	 * @description 功能描述：获取黑名单产品功能 0.未开通1.已开通
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00005() {
        return p00005;
    }

    /**
	 * @description 功能描述：设置黑名单产品功能 0.未开通1.已开通
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param p00005
	 * @return 返回参数： void
	 */
    public void setP00005(java.lang.Short p00005) {
        this.p00005 = p00005;
    }
    
    /**
	 * @description 功能描述：获取黑名单产品版本0.简版1.【明细版1】 显示最新的催收记录2.【明细版2】显示逾期最高的催收记录3.【明细版3】显示所有的记录
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00005Param() {
        return p00005Param;
    }

    /**
	 * @description 功能描述：设置黑名单产品版本0.简版1.【明细版1】 显示最新的催收记录2.【明细版2】显示逾期最高的催收记录3.【明细版3】显示所有的记录
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param p00005Param
	 * @return 返回参数： void
	 */
    public void setP00005Param(java.lang.Short p00005Param) {
        this.p00005Param = p00005Param;
    }
    
    
    public CompanyInfo()
    {
    }
}
