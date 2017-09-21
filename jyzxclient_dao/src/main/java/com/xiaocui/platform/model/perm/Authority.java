package com.xiaocui.platform.model.perm;

public class Authority {
    
    //标识列
    private java.lang.Long id;
    
    //权限模板表ID
    private java.lang.Long authTplId;
    
    //权限条件UUID
    private java.lang.String code;
    
    //权限过滤条件ID
    private java.lang.Long filterId;
    
    //类型 1.叶子节点 2.Action 3帮助
    private java.lang.Long type;
    
    
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
	 * @description 功能描述：获取权限模板表id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getAuthTplId() {
        return authTplId;
    }

    /**
	 * @description 功能描述：设置权限模板表id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param authTplId
	 * @return 返回参数： void
	 */
    public void setAuthTplId(java.lang.Long authTplId) {
        this.authTplId = authTplId;
    }
    
    /**
	 * @description 功能描述：获取权限条件uuid
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getCode() {
        return code;
    }

    /**
	 * @description 功能描述：设置权限条件uuid
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param code
	 * @return 返回参数： void
	 */
    public void setCode(java.lang.String code) {
        this.code = code;
    }
    
    /**
	 * @description 功能描述：获取权限过滤条件id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getFilterId() {
        return filterId;
    }

    /**
	 * @description 功能描述：设置权限过滤条件id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param filterId
	 * @return 返回参数： void
	 */
    public void setFilterId(java.lang.Long filterId) {
        this.filterId = filterId;
    }
    
    /**
	 * @description 功能描述：获取类型 1.叶子节点 2.action 3帮助
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getType() {
        return type;
    }

    /**
	 * @description 功能描述：设置类型 1.叶子节点 2.action 3帮助
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param type
	 * @return 返回参数： void
	 */
    public void setType(java.lang.Long type) {
        this.type = type;
    }
    
    
    public Authority()
    {
    }
}
