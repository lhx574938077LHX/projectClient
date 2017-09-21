package com.xiaocui.dao.model;

public class CompanyInfoBean {
    
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
    
    //积分倍数
    private java.lang.Short integralMult;
    
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
    
    //黑名单产品功能 0.未开通1.已开通
    private java.lang.Short p00005;
    
    //黑名单产品版本0.简版1.【明细版1】 显示最新的催收记录2.【明细版2】显示逾期最高的催收记录3.【明细版3】显示所有的记录
    private java.lang.Short p00005Param;
    
    //三要素认证接口 功能 0.未开通1.已开通
    private java.lang.Short p00006;
    
    //三要素认证接口积分价格
    private java.lang.Long p00006Price;
    
    //身份证照片接口 功能 0.未开通1.已开通
    private java.lang.Short p00007;
    
    //身份证照片接口积分价格
    private java.lang.Long p00007Price;
    
    //工商信息查询接口 功能 0.未开通1.已开通
    private java.lang.Short p00008;
    
    //工商信息查询接口积分价格
    private java.lang.Long p00008Price;
    
    //是否检测透支额度 0.未开通1.已开通
    private java.lang.Short p00009;
    
    //四要素认证接口 功能 0.未开通1.已开通
    private java.lang.Short p00010;
    
    //四要素认证接口积分价格
    private java.lang.Long p00010Price;
    
    //二要素认证接口 功能 0.未开通1.已开通
    private java.lang.Short p00011;
    
    //二要素认证接口积分价格
    private java.lang.Long p00011Price;
    
    //创建时间
    private java.lang.Long recTime;
    
    //数据源类型 0.公开1.私有
    private java.lang.Short sourceType;
    
    //公司等级
    private java.lang.Short companyLevel;
    
    //要排除的公司CODE 用|号分割
    private java.lang.String excluded;
    
    //绑定IP 用|号分割
    private java.lang.String bindIp;
    
    //积分透支额度
    private java.lang.Long integralOverdraft;
    
    //开始计费时间
    private java.lang.Long startChargingTime;
    
    //公司私钥
    private java.lang.String privateKeyP;
    
    //公司公钥
    private java.lang.String publicKeyP;
    
    //91私钥
    private java.lang.String privateKeyS;
    
    //91公钥
    private java.lang.String publicKeyS;
    
    
    /**
	 * @description 功能描述：获取标识列
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getId() {
        return id;
    }

    /**
	 * @description 功能描述：设置标识列
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param id
	 * @return 返回参数： void
	 */
    public void setId(java.lang.Long id) {
        this.id = id;
    }
    
    /**
	 * @description 功能描述：获取通信签名
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getConnSign() {
        return connSign;
    }

    /**
	 * @description 功能描述：设置通信签名
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param connSign
	 * @return 返回参数： void
	 */
    public void setConnSign(java.lang.String connSign) {
        this.connSign = connSign;
    }
    
    /**
	 * @description 功能描述：获取公司名
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getCompanyName() {
        return companyName;
    }

    /**
	 * @description 功能描述：设置公司名
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param companyName
	 * @return 返回参数： void
	 */
    public void setCompanyName(java.lang.String companyName) {
        this.companyName = companyName;
    }
    
    /**
	 * @description 功能描述：获取公司编码
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getCompanyCode() {
        return companyCode;
    }

    /**
	 * @description 功能描述：设置公司编码
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param companyCode
	 * @return 返回参数： void
	 */
    public void setCompanyCode(java.lang.String companyCode) {
        this.companyCode = companyCode;
    }
    
    /**
	 * @description 功能描述：获取积分
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getIntegral() {
        return integral;
    }

    /**
	 * @description 功能描述：设置积分
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param integral
	 * @return 返回参数： void
	 */
    public void setIntegral(java.lang.Long integral) {
        this.integral = integral;
    }
    
    /**
	 * @description 功能描述：获取积分倍数
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getIntegralMult() {
        return integralMult;
    }

    /**
	 * @description 功能描述：设置积分倍数
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param integralMult
	 * @return 返回参数： void
	 */
    public void setIntegralMult(java.lang.Short integralMult) {
        this.integralMult = integralMult;
    }
    
    /**
	 * @description 功能描述：获取当前状态 0.未知1.待审批 2.未通过审核 3.已开通 4.已停用
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getState() {
        return state;
    }

    /**
	 * @description 功能描述：设置当前状态 0.未知1.待审批 2.未通过审核 3.已开通 4.已停用
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param state
	 * @return 返回参数： void
	 */
    public void setState(java.lang.Short state) {
        this.state = state;
    }
    
    /**
	 * @description 功能描述：获取服务地址
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getServiceUrl() {
        return serviceUrl;
    }

    /**
	 * @description 功能描述：设置服务地址
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param serviceUrl
	 * @return 返回参数： void
	 */
    public void setServiceUrl(java.lang.String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
    
    /**
	 * @description 功能描述：获取3001被查 当前状态 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00001() {
        return p00001;
    }

    /**
	 * @description 功能描述：设置3001被查 当前状态 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00001
	 * @return 返回参数： void
	 */
    public void setP00001(java.lang.Short p00001) {
        this.p00001 = p00001;
    }
    
    /**
	 * @description 功能描述：获取3002报送 当前状态 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00002() {
        return p00002;
    }

    /**
	 * @description 功能描述：设置3002报送 当前状态 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00002
	 * @return 返回参数： void
	 */
    public void setP00002(java.lang.Short p00002) {
        this.p00002 = p00002;
    }
    
    /**
	 * @description 功能描述：获取加密方式 0.未开通1.签名通信2.rsa加密
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00003() {
        return p00003;
    }

    /**
	 * @description 功能描述：设置加密方式 0.未开通1.签名通信2.rsa加密
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00003
	 * @return 返回参数： void
	 */
    public void setP00003(java.lang.Short p00003) {
        this.p00003 = p00003;
    }
    
    /**
	 * @description 功能描述：获取1003功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00004() {
        return p00004;
    }

    /**
	 * @description 功能描述：设置1003功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00004
	 * @return 返回参数： void
	 */
    public void setP00004(java.lang.Short p00004) {
        this.p00004 = p00004;
    }
    
    /**
	 * @description 功能描述：获取黑名单产品功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00005() {
        return p00005;
    }

    /**
	 * @description 功能描述：设置黑名单产品功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00005
	 * @return 返回参数： void
	 */
    public void setP00005(java.lang.Short p00005) {
        this.p00005 = p00005;
    }
    
    /**
	 * @description 功能描述：获取黑名单产品版本0.简版1.【明细版1】 显示最新的催收记录2.【明细版2】显示逾期最高的催收记录3.【明细版3】显示所有的记录
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00005Param() {
        return p00005Param;
    }

    /**
	 * @description 功能描述：设置黑名单产品版本0.简版1.【明细版1】 显示最新的催收记录2.【明细版2】显示逾期最高的催收记录3.【明细版3】显示所有的记录
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00005Param
	 * @return 返回参数： void
	 */
    public void setP00005Param(java.lang.Short p00005Param) {
        this.p00005Param = p00005Param;
    }
    
    /**
	 * @description 功能描述：获取三要素认证接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00006() {
        return p00006;
    }

    /**
	 * @description 功能描述：设置三要素认证接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00006
	 * @return 返回参数： void
	 */
    public void setP00006(java.lang.Short p00006) {
        this.p00006 = p00006;
    }
    
    /**
	 * @description 功能描述：获取三要素认证接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getP00006Price() {
        return p00006Price;
    }

    /**
	 * @description 功能描述：设置三要素认证接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00006Price
	 * @return 返回参数： void
	 */
    public void setP00006Price(java.lang.Long p00006Price) {
        this.p00006Price = p00006Price;
    }
    
    /**
	 * @description 功能描述：获取身份证照片接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00007() {
        return p00007;
    }

    /**
	 * @description 功能描述：设置身份证照片接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00007
	 * @return 返回参数： void
	 */
    public void setP00007(java.lang.Short p00007) {
        this.p00007 = p00007;
    }
    
    /**
	 * @description 功能描述：获取身份证照片接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getP00007Price() {
        return p00007Price;
    }

    /**
	 * @description 功能描述：设置身份证照片接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00007Price
	 * @return 返回参数： void
	 */
    public void setP00007Price(java.lang.Long p00007Price) {
        this.p00007Price = p00007Price;
    }
    
    /**
	 * @description 功能描述：获取工商信息查询接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00008() {
        return p00008;
    }

    /**
	 * @description 功能描述：设置工商信息查询接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00008
	 * @return 返回参数： void
	 */
    public void setP00008(java.lang.Short p00008) {
        this.p00008 = p00008;
    }
    
    /**
	 * @description 功能描述：获取工商信息查询接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getP00008Price() {
        return p00008Price;
    }

    /**
	 * @description 功能描述：设置工商信息查询接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00008Price
	 * @return 返回参数： void
	 */
    public void setP00008Price(java.lang.Long p00008Price) {
        this.p00008Price = p00008Price;
    }
    
    /**
	 * @description 功能描述：获取是否检测透支额度 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00009() {
        return p00009;
    }

    /**
	 * @description 功能描述：设置是否检测透支额度 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00009
	 * @return 返回参数： void
	 */
    public void setP00009(java.lang.Short p00009) {
        this.p00009 = p00009;
    }
    
    /**
	 * @description 功能描述：获取四要素认证接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00010() {
        return p00010;
    }

    /**
	 * @description 功能描述：设置四要素认证接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00010
	 * @return 返回参数： void
	 */
    public void setP00010(java.lang.Short p00010) {
        this.p00010 = p00010;
    }
    
    /**
	 * @description 功能描述：获取四要素认证接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getP00010Price() {
        return p00010Price;
    }

    /**
	 * @description 功能描述：设置四要素认证接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00010Price
	 * @return 返回参数： void
	 */
    public void setP00010Price(java.lang.Long p00010Price) {
        this.p00010Price = p00010Price;
    }
    
    /**
	 * @description 功能描述：获取二要素认证接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getP00011() {
        return p00011;
    }

    /**
	 * @description 功能描述：设置二要素认证接口 功能 0.未开通1.已开通
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00011
	 * @return 返回参数： void
	 */
    public void setP00011(java.lang.Short p00011) {
        this.p00011 = p00011;
    }
    
    /**
	 * @description 功能描述：获取二要素认证接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getP00011Price() {
        return p00011Price;
    }

    /**
	 * @description 功能描述：设置二要素认证接口积分价格
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param p00011Price
	 * @return 返回参数： void
	 */
    public void setP00011Price(java.lang.Long p00011Price) {
        this.p00011Price = p00011Price;
    }
    
    /**
	 * @description 功能描述：获取创建时间
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getRecTime() {
        return recTime;
    }

    /**
	 * @description 功能描述：设置创建时间
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param recTime
	 * @return 返回参数： void
	 */
    public void setRecTime(java.lang.Long recTime) {
        this.recTime = recTime;
    }
    
    /**
	 * @description 功能描述：获取数据源类型 0.公开1.私有
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getSourceType() {
        return sourceType;
    }

    /**
	 * @description 功能描述：设置数据源类型 0.公开1.私有
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param sourceType
	 * @return 返回参数： void
	 */
    public void setSourceType(java.lang.Short sourceType) {
        this.sourceType = sourceType;
    }
    
    /**
	 * @description 功能描述：获取公司等级
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getCompanyLevel() {
        return companyLevel;
    }

    /**
	 * @description 功能描述：设置公司等级
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param companyLevel
	 * @return 返回参数： void
	 */
    public void setCompanyLevel(java.lang.Short companyLevel) {
        this.companyLevel = companyLevel;
    }
    
    /**
	 * @description 功能描述：获取要排除的公司code 用|号分割
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getExcluded() {
        return excluded;
    }

    /**
	 * @description 功能描述：设置要排除的公司code 用|号分割
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param excluded
	 * @return 返回参数： void
	 */
    public void setExcluded(java.lang.String excluded) {
        this.excluded = excluded;
    }
    
    /**
	 * @description 功能描述：获取绑定ip 用|号分割
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getBindIp() {
        return bindIp;
    }

    /**
	 * @description 功能描述：设置绑定ip 用|号分割
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param bindIp
	 * @return 返回参数： void
	 */
    public void setBindIp(java.lang.String bindIp) {
        this.bindIp = bindIp;
    }
    
    /**
	 * @description 功能描述：获取积分透支额度
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getIntegralOverdraft() {
        return integralOverdraft;
    }

    /**
	 * @description 功能描述：设置积分透支额度
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param integralOverdraft
	 * @return 返回参数： void
	 */
    public void setIntegralOverdraft(java.lang.Long integralOverdraft) {
        this.integralOverdraft = integralOverdraft;
    }
    
    /**
	 * @description 功能描述：获取开始计费时间
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getStartChargingTime() {
        return startChargingTime;
    }

    /**
	 * @description 功能描述：设置开始计费时间
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param startChargingTime
	 * @return 返回参数： void
	 */
    public void setStartChargingTime(java.lang.Long startChargingTime) {
        this.startChargingTime = startChargingTime;
    }
    
    /**
	 * @description 功能描述：获取公司私钥
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getPrivateKeyP() {
        return privateKeyP;
    }

    /**
	 * @description 功能描述：设置公司私钥
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param privateKeyP
	 * @return 返回参数： void
	 */
    public void setPrivateKeyP(java.lang.String privateKeyP) {
        this.privateKeyP = privateKeyP;
    }
    
    /**
	 * @description 功能描述：获取公司公钥
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getPublicKeyP() {
        return publicKeyP;
    }

    /**
	 * @description 功能描述：设置公司公钥
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param publicKeyP
	 * @return 返回参数： void
	 */
    public void setPublicKeyP(java.lang.String publicKeyP) {
        this.publicKeyP = publicKeyP;
    }
    
    /**
	 * @description 功能描述：获取91私钥
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getPrivateKeyS() {
        return privateKeyS;
    }

    /**
	 * @description 功能描述：设置91私钥
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param privateKeyS
	 * @return 返回参数： void
	 */
    public void setPrivateKeyS(java.lang.String privateKeyS) {
        this.privateKeyS = privateKeyS;
    }
    
    /**
	 * @description 功能描述：获取91公钥
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getPublicKeyS() {
        return publicKeyS;
    }

    /**
	 * @description 功能描述：设置91公钥
	 * @createdate 创建日期 2017-01-13 18:38:08
	 * @params 传入参数：@param publicKeyS
	 * @return 返回参数： void
	 */
    public void setPublicKeyS(java.lang.String publicKeyS) {
        this.publicKeyS = publicKeyS;
    }
    
    
    public CompanyInfoBean()
    {
    }
}
