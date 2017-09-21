package com.xiaocui.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestBlackQuery {
	
	/**
	 * 读取信息
	 */
	public static String readFile(String filePath) throws Exception {
		File file = new File(filePath);
		StringBuilder sb = new StringBuilder();
		String s = "";
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		br.close();
		return sb.toString();
	}
	/**
	 * 读取信息
	 */
	public static String readFile(InputStream in) throws Exception {
		StringBuilder sb = new StringBuilder();
		String s = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		br.close();
		return sb.toString();
	}
	
	/**
	 * 查询参数信息
	 * @param companyCode	机构码
	 * @param authorCode	授权码
	 * @param idNo			身份证号
	 * @param name			姓名
	 * @param phone			手机号
	 * @return
	 */
	public static String paramsJson(String authorCode, String idNo, String name, String phone){
		StringBuffer sb = new StringBuffer();
		sb.append("{")
		.append("\"authorCode\":")
		.append("\"" + authorCode + "\",")
		.append("\"idNo\":")
		.append("\"" + idNo + "\",")
		.append("\"name\":")
		.append("\"" + name + "\",")
		.append("\"phone\":")
		.append("\"" + phone + "\"")
		.append("}");
		return sb.toString();
	}

}
