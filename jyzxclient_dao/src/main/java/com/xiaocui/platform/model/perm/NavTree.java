package com.xiaocui.platform.model.perm;

public class NavTree {
    
    //标识列
    private java.lang.Long id;
    
    //父ID
    private java.lang.Long pid;
    
    //标签ID
    private java.lang.String oid;
    
    //是否展开0.false 1.true
    private java.lang.Short expanded;
    
    //标签名
    private java.lang.String text;
    
    //节点类型：0.导航Tab.1导航折叠2.导航树形3.树形节点
    private java.lang.Short nodeType;
    
    //图标css
    private java.lang.String iconcls;
    
    //排序字段
    private java.lang.Long sort;
    
    //导航节点
    private java.lang.String guid;
    
    //功能视图UUID
    private java.lang.String funcViewUuid;
    
    
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
	 * @description 功能描述：获取父id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getPid() {
        return pid;
    }

    /**
	 * @description 功能描述：设置父id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param pid
	 * @return 返回参数： void
	 */
    public void setPid(java.lang.Long pid) {
        this.pid = pid;
    }
    
    /**
	 * @description 功能描述：获取标签id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getOid() {
        return oid;
    }

    /**
	 * @description 功能描述：设置标签id
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param oid
	 * @return 返回参数： void
	 */
    public void setOid(java.lang.String oid) {
        this.oid = oid;
    }
    
    /**
	 * @description 功能描述：获取是否展开0.false 1.true
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getExpanded() {
        return expanded;
    }

    /**
	 * @description 功能描述：设置是否展开0.false 1.true
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param expanded
	 * @return 返回参数： void
	 */
    public void setExpanded(java.lang.Short expanded) {
        this.expanded = expanded;
    }
    
    /**
	 * @description 功能描述：获取标签名
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getText() {
        return text;
    }

    /**
	 * @description 功能描述：设置标签名
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param text
	 * @return 返回参数： void
	 */
    public void setText(java.lang.String text) {
        this.text = text;
    }
    
    /**
	 * @description 功能描述：获取节点类型：0.导航tab.1导航折叠2.导航树形3.树形节点
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Short
	 */
    public java.lang.Short getNodeType() {
        return nodeType;
    }

    /**
	 * @description 功能描述：设置节点类型：0.导航tab.1导航折叠2.导航树形3.树形节点
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param nodeType
	 * @return 返回参数： void
	 */
    public void setNodeType(java.lang.Short nodeType) {
        this.nodeType = nodeType;
    }
    
    /**
	 * @description 功能描述：获取图标css
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getIconcls() {
        return iconcls;
    }

    /**
	 * @description 功能描述：设置图标css
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param iconcls
	 * @return 返回参数： void
	 */
    public void setIconcls(java.lang.String iconcls) {
        this.iconcls = iconcls;
    }
    
    /**
	 * @description 功能描述：获取排序字段
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.Long
	 */
    public java.lang.Long getSort() {
        return sort;
    }

    /**
	 * @description 功能描述：设置排序字段
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param sort
	 * @return 返回参数： void
	 */
    public void setSort(java.lang.Long sort) {
        this.sort = sort;
    }
    
    /**
	 * @description 功能描述：获取导航节点
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getGuid() {
        return guid;
    }

    /**
	 * @description 功能描述：设置导航节点
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param guid
	 * @return 返回参数： void
	 */
    public void setGuid(java.lang.String guid) {
        this.guid = guid;
    }
    
    /**
	 * @description 功能描述：获取功能视图uuid
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @return 返回参数： java.lang.String
	 */
    public java.lang.String getFuncViewUuid() {
        return funcViewUuid;
    }

    /**
	 * @description 功能描述：设置功能视图uuid
	 * @createdate 创建日期 2016-04-11 15:45:38
	 * @params 传入参数：@param funcViewUuid
	 * @return 返回参数： void
	 */
    public void setFuncViewUuid(java.lang.String funcViewUuid) {
        this.funcViewUuid = funcViewUuid;
    }
    
    
    public NavTree()
    {
    }
}
