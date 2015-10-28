package com.xiaocui.service;

import com.xiaocui.entity.PkgHeader;

public interface ITask {
	/**
	 * 
	* @Title: doTask 
	* @Description: 任务处理
	* @param @param reqPkg	请求报文
	* @param @param rspPkg	响应报文
	* @param @return    设定文件 
	* @return PkgHeader    返回类型 
	* @throws
	 */
	public PkgHeader doTask(PkgHeader reqPkg,PkgHeader rspPkg);
}

