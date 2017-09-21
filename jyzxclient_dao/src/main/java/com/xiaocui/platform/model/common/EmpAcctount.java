package com.xiaocui.platform.model.common;

public class EmpAcctount {
    
    //标识列
    private java.lang.Long id;
    
    //登陆名
    private java.lang.String username;
    
    //登陆密码
    private java.lang.String password;
    
    //权限模板表ID
    private java.lang.Long authTplId;
    
    //用户状态:1.已启用 2.已销户
    private java.lang.Short state;
    
    //最后一次登陆时间
    private java.lang.Long lastLoginTime;
    
    //真实姓名
    private java.lang.String realName;
    
    //电话号码
    private java.lang.String telephone;
    
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
	 * @description 功能描述：获取登陆名
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getUsername() {
        return username;
    }

    /**
	 * @description 功能描述：设置登陆名
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param username
	 * @return 返回参数： void
	 */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }
    
    /**
	 * @description 功能描述：获取登陆密码
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getPassword() {
        return password;
    }

    /**
	 * @description 功能描述：设置登陆密码
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param password
	 * @return 返回参数： void
	 */
    public void setPassword(java.lang.String password) {
        this.password = password;
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
	 * @description 功能描述：获取用户状态:1.已启用 2.已销户
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getState() {
        return state;
    }

    /**
	 * @description 功能描述：设置用户状态:1.已启用 2.已销户
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param state
	 * @return 返回参数： void
	 */
    public void setState(java.lang.Short state) {
        this.state = state;
    }
    
    /**
	 * @description 功能描述：获取最后一次登陆时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getLastLoginTime() {
        return lastLoginTime;
    }

    /**
	 * @description 功能描述：设置最后一次登陆时间
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param lastLoginTime
	 * @return 返回参数： void
	 */
    public void setLastLoginTime(java.lang.Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    /**
	 * @description 功能描述：获取真实姓名
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getRealName() {
        return realName;
    }

    /**
	 * @description 功能描述：设置真实姓名
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param realName
	 * @return 返回参数： void
	 */
    public void setRealName(java.lang.String realName) {
        this.realName = realName;
    }
    
    /**
	 * @description 功能描述：获取电话号码
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getTelephone() {
        return telephone;
    }

    /**
	 * @description 功能描述：设置电话号码
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param telephone
	 * @return 返回参数： void
	 */
    public void setTelephone(java.lang.String telephone) {
        this.telephone = telephone;
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
    
    
    public EmpAcctount()
    {
    }
}
