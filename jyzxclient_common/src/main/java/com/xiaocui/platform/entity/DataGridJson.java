package com.xiaocui.platform.entity;

import java.util.ArrayList;
import java.util.List;

public class DataGridJson {
	
	private boolean result = true;
	
	private String msg = "操作成功！";
	
	@SuppressWarnings("rawtypes")
	private List datas = new ArrayList();
		
	private long dataCount  = 0;

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

	@SuppressWarnings("rawtypes")
	public List getDatas() {
		return datas;
	}
	
	@SuppressWarnings("rawtypes")
	public void setDatas(List datas) {
		this.datas = datas;
	}

	public long getDataCount() {
		return dataCount;
	}

	public void setDataCount(long dataCount) {
		this.dataCount = dataCount;
	}

	@Override
	public String toString() {
		return "DataGridJson [result=" + result + ", msg=" + msg + ", datas="
				+ datas + ", dataCount=" + dataCount + "]";
	}


}
