package com.xiaocui.controller;

import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaocui.platform.common.DateUtils;
import com.xiaocui.platform.common.ValidateCode;

@Controller
@RequestMapping("common")
public class CommonController {
	private final static Logger logger = Logger.getLogger(CommonController.class);
	
	@RequestMapping("getVCode")
	public void getAuditCode(HttpServletRequest request, HttpServletResponse response) {
		OutputStream os = null;
		try {
			ValidateCode vcode = new ValidateCode();
			os = response.getOutputStream();
			String vcodestr = vcode.getRandVCode();
			vcode.CreateGifVCode(os, vcodestr);
			request.getSession().setAttribute("vCode", vcodestr);
		} catch (Exception e) {
//			logger.error("91zhengxin Exception: ", e);
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (Exception e) {
//					logger.error("91zhengxin Exception: ", e);
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping("serverTime")
	@ResponseBody
	public String serverTime(HttpServletRequest request, HttpServletResponse response) {
		return DateUtils.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
}
