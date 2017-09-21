package com.xiaocui.platform.entity;

public class Task3001Json {
	
	private boolean result = false;
	private String msg = "操作失败！"; 
	private Object data = new Object();
	private Object redata = new Object();
	
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getRedata() {
		return redata;
	}
	public void setRedata(Object redata) {
		this.redata = redata;
	}

}
