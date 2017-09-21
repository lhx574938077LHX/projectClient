package com.xiaocui.platform.model.perm;

public class AuthTemplate {
    
    //标识列
    private java.lang.Long id;
    
    //名称
    private java.lang.String name;
    
    //备注
    private java.lang.String remark;
    
    
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
	 * @description 功能描述：获取名称
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getName() {
        return name;
    }

    /**
	 * @description 功能描述：设置名称
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param name
	 * @return 返回参数： void
	 */
    public void setName(java.lang.String name) {
        this.name = name;
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
    
    
    public AuthTemplate()
    {
    }
}
