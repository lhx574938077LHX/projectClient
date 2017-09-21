package com.xiaocui.platform.model.search;

public class SearchCount {
    
    //标识列
    private java.lang.Long id;
    
    //公司ID
    private java.lang.Long companyId;
    
    //公司编码
    private java.lang.String companyCode;
    
    //查询总数
    private java.lang.Integer searchCount;
    
    //创建时间
    private java.lang.Long recTime;
    
    
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
	 * @description 功能描述：获取公司id
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getCompanyId() {
        return companyId;
    }

    /**
	 * @description 功能描述：设置公司id
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param companyId
	 * @return 返回参数： void
	 */
    public void setCompanyId(java.lang.Long companyId) {
        this.companyId = companyId;
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
	 * @description 功能描述：获取查询总数
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @return 返回参数： java.lang.Integer
	 */
    public java.lang.Integer getSearchCount() {
        return searchCount;
    }

    /**
	 * @description 功能描述：设置查询总数
	 * @createdate 创建日期 2016-04-11 15:45:37
	 * @params 传入参数：@param searchCount
	 * @return 返回参数： void
	 */
    public void setSearchCount(java.lang.Integer searchCount) {
        this.searchCount = searchCount;
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
    
    
    public SearchCount()
    {
    }
}
