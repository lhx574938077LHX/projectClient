package com.xiaocui.entity;

public class BlackListInfo {
	private boolean result;
	private String msg;
	private Integer dataCount;
	
	private Datas datas;

	public boolean isResult() {
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

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}

	public Datas getDatas() {
		return datas;
	}

	public void setDatas(Datas datas) {
		this.datas = datas;
	}
	
	
}
