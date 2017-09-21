package com.xiaocui.entity;

import java.io.Serializable;

public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	String blackLevel;
	String blackReason;
	public String getBlackLevel() {
		return blackLevel;
	}
	public void setBlackLevel(String blackLevel) {
		this.blackLevel = blackLevel;
	}
	public String getBlackReason() {
		return blackReason;
	}
	public void setBlackReason(String blackReason) {
		this.blackReason = blackReason;
	}
	
	
}
