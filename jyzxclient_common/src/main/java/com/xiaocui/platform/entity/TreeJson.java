package com.xiaocui.platform.entity;

import java.util.ArrayList;
import java.util.List;

public class TreeJson {
	private String id = "";
	private String text = "";
	
	private boolean expanded = false;
	
	private boolean leaf = true;
	
	private String iconCls = "";
	
	private long dbid;
	
	private List<TreeJson> children = new ArrayList<TreeJson>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public List<TreeJson> getChildren() {
		return children;
	}

	public void setChildren(List<TreeJson> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public long getDbid() {
		return dbid;
	}

	public void setDbid(long dbid) {
		this.dbid = dbid;
	}
	

}
