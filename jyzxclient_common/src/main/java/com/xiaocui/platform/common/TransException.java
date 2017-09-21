package com.xiaocui.platform.common;

public class TransException extends  RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String retCode;
	private String msg;
	private byte[] data;
	
	
	public byte[] getData() {
		return data;
	}


	
	public void setData(byte[] data) {
		this.data = data;
	}


	public String getRetCode() {
		return retCode;
	}

	
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	
	public String getMsg() {
		return msg;
	}

	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public TransException(String msg)
	{
		super(msg);
		this.msg = msg;
	}
	
	public TransException(String retCode ,String msg)
	{
		super(msg);
		this.msg = msg;
		this.retCode = retCode;
	}
	
	public TransException(String retCode ,String msg,byte[] data)
	{
		super(msg);
		this.msg = msg;
		this.retCode = retCode;
		this.data = data;
	}
}

