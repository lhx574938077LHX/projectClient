package com.xiaocui.platform.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class StringUtils {
	private static Logger logger = Logger.getLogger(StringUtils.class);
	/**
	 * 判断字符串是否为空(自动截取首尾空白)
	 * 
	 * @param str
	 *            源字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return isEmpty(str, true);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            源字符串
	 * @param trim
	 *            是否截取首尾空白
	 * @return
	 */
	public static boolean isEmpty(String str, boolean trim) {
		return str == null ? true : "".equals(str.trim());
	}
	
	/**
	 * 中文数字转换为阿拉伯数
	 * 
	 * @param String
	 *            s
	 */
	public static int cnNumToInt(String s) {
		int result = 0;
		int yi = 1;// 记录高级单位
		int wan = 1;// 记录高级单位
		int ge = 1;// 记录单位
		char c = s.charAt(0);
		int temp = 0;// 记录数值
		switch (c) {
		// 数值
		case '〇':
		case '零':
			temp = 0;
			break;
		case '一':
			temp = 1 * ge * wan * yi;
			ge = 1;
			break;
		case '二':
			temp = 2 * ge * wan * yi;
			ge = 1;
			break;
		case '三':
			temp = 3 * ge * wan * yi;
			ge = 1;
			break;
		case '四':
			temp = 4 * ge * wan * yi;
			ge = 1;
			break;
		case '五':
			temp = 5 * ge * wan * yi;
			ge = 1;
			break;
		case '六':
			temp = 6 * ge * wan * yi;
			ge = 1;
			break;
		case '七':
			temp = 7 * ge * wan * yi;
			ge = 1;
			break;
		case '八':
			temp = 8 * ge * wan * yi;
			ge = 1;
			break;
		case '九':
			temp = 9 * ge * wan * yi;
			ge = 1;
			break;
		// 单位，前缀是单数字
		case '十':
			ge = 10;
			break;
		case '百':
			ge = 100;
			break;
		case '千':
			ge = 1000;
			break;
		// 高级单位，前缀可以是多个数字
		case '万':
			wan = 10000;
			ge = 1;
			break;
		case '亿':
			yi = 100000000;
			wan = 1;
			ge = 1;
			break;
		default:
			return -1;
		}
		result += temp;
		if (ge > 1) {
			result += 1 * ge * wan * yi;
		}
		return result;
	}
	
	public static int switchNumber(String str) {
		char c = str.charAt(0);
		int temp = 0;
		switch (c) {
		// 数值
		case '〇':
		case '零':
			temp = 0;
			break;
		case '一':
			temp = 1;
			break;
		case '二':
			temp = 2;
			break;
		case '三':
			temp = 3;
			break;
		case '四':
			temp = 4;
			break;
		case '五':
			temp = 5;
			break;
		case '六':
			temp = 6;
			break;
		case '七':
			temp = 7;
			break;
		case '八':
			temp = 8;
			break;
		case '九':
			temp = 9;
			break;
		// 单位，前缀是单数字
		case '十':
			temp = 10;
			break;
		}
		return temp;
	}
	
	public static String getUTF8StringFromInputStream(InputStream inputStream,String encoding)
	{
		String result = null;
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	    try
	    {
	    	byte[] buf = new byte[4096];  
		    int num = 0;  
		    while ((num = inputStream.read(buf,0,4096)) > 0) {  
		        swapStream.write(buf, 0, num);  
		    }  
		    byte[] data = swapStream.toByteArray();
		    result = new String(data,encoding);
	    }
	    catch(Exception ex)
	    {
	    	logger.error(ex);
	    }
	    finally {
	    	try {if(swapStream != null)swapStream.close();} catch (IOException e) {}
		}
	    
	    return result;
	}
}
