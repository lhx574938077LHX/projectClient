package com.xiaocui.entity;

public class BaiduRequest {
	//事件类型
	private String eventType;
	//请求来源
	private String sourceName;
	//机构编码
	private String agencyCode;
	//请求id
	private String reqId;
	//签名类型
	private String signType;
	//签名
	private String sign;
	//请求时间戳
	private String timestamp;
	//身份证号
	private String prcid;
	//姓名
	private String name;
	//手机号
	private String phoneNumber;
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getPrcid() {
		return prcid;
	}
	public void setPrcid(String prcid) {
		this.prcid = prcid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
