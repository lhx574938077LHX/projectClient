package com.xiaocui.platform.entity;

public class HttpResponseData {

	// 保存请求后得到的cookie
	private String setCookie;
	// 保存请求后返回的响应数据
	private String responseString;

	public HttpResponseData() {

	}

	public String getSetCookie() {
		return setCookie;
	}

	public void setSetCookie(String setCookie) {
		this.setCookie = setCookie;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	@Override
	public String toString() {
		return "HttpResponseData [setCookie=" + setCookie + ", responseString=" + responseString + "]";
	}
	
}
