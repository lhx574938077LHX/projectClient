package com.xiaocui.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspose.cells.Cells;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.xiaocui.platform.common.StringUtils;
import com.xiaocui.platform.entity.AUDJson;
import com.xiaocui.platform.entity.DataGridJson;
import com.xiaocui.platform.model.ext.JoinConditionExt;
import com.xiaocui.platform.model.search.CompanyInfo;
import com.xiaocui.platform.model.search.JoinCondition;
import com.xiaocui.service.AccountServiceImpl;

@Controller
@RequestMapping("account")
public class AccountController {
	
	private final static Logger logger = Logger.getLogger(AccountController.class);
	
	@Autowired
	private AccountServiceImpl accountService;
	
	@RequestMapping("queryAccountLJP")
	@ResponseBody
	public DataGridJson queryAccountLJP(HttpServletRequest request, HttpServletResponse response, int start, int limit,
			 @RequestParam(defaultValue = "0")String cpName,@RequestParam(defaultValue = "0")String cpCode){
		DataGridJson result = new DataGridJson();
		try {
			result = accountService.queryAccountList(start,limit,cpName,cpCode);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setDataCount(0);
			result.setDatas(null);
		}
		return result;
	}
	
	@RequestMapping("addAccount")
	@ResponseBody
	public AUDJson addAccount(HttpServletRequest request, HttpServletResponse response,CompanyInfo companyInfo){
		AUDJson result = new AUDJson();
		try {
			result = accountService.addAccount(companyInfo);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setData(null);
			result.setMsg("系统异常！");
			result.setResult(false);
		}		
		return result;
	}
	
	@RequestMapping("updateAccount")
	@ResponseBody
	public AUDJson updateAccount(HttpServletRequest request, HttpServletResponse response,CompanyInfo companyInfo){
		AUDJson result = new AUDJson();
		try {
			result = accountService.updateAccount(companyInfo);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setData(null);
			result.setMsg("系统异常！");
			result.setResult(false);
		}	
		return result;
	}
	
	/**
	 * 查询查询信息
	 */
	@RequestMapping("checkAccount")
	@ResponseBody	
	public DataGridJson checkAccount(HttpServletRequest request, HttpServletResponse response,int start, int limit,Long id
			,@RequestParam(defaultValue = "0")String trxNo){
		DataGridJson result = new DataGridJson();
		try {
			result = accountService.checkAccount(start,limit,id,trxNo);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setDataCount(0);
			result.setDatas(null);
		}	
		return result;
	}
	/**
	 * 查询查询数据
	 */	
	@RequestMapping("selectSearchResult")
	@ResponseBody	
	public DataGridJson selectSearchResult(HttpServletRequest request, HttpServletResponse response,String trxNo){
		DataGridJson result = new DataGridJson();
		try {
			result = accountService.selectSearchResult(trxNo);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setDataCount(0);
			result.setDatas(null);
		}	
		return result;
	}
	
	@RequestMapping("reSendSearchResult")
	@ResponseBody
	public AUDJson reSendSearchResult(HttpServletRequest request, HttpServletResponse response,long id){
		AUDJson result = new AUDJson();
		try {
			result = accountService.reSendSearchResult(id);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setData(null);
			result.setMsg("系统异常！");
			result.setResult(false);			
		}
		return result;
	}
	
	/**
	 * 客户对接情况查询
	 */
	@RequestMapping("selectJoinCondition")
	@ResponseBody	
	public DataGridJson selectJoinCondition(HttpServletRequest request, HttpServletResponse response,int start, int limit
			,@RequestParam(defaultValue = "0")String cpName,@RequestParam(defaultValue = "0")String bdName
			,@RequestParam(defaultValue = "-1")short joinState,@RequestParam(defaultValue = "0")long startdate
			,@RequestParam(defaultValue = "0")long enddate){
		DataGridJson result = new DataGridJson();
		try {
			result = accountService.selectJoinCondition(start,limit,cpName,bdName,joinState,startdate,enddate);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setDataCount(0);
			result.setDatas(null);
		}	
		return result;
	}
	
	/**
	 * 查询单个对接客户
	 * @param request
	 * @param response
	 * @param companyId
	 * @return
	 */
	@RequestMapping("selectJoinConditionOne")
	@ResponseBody
	public AUDJson selectJoinConditionOne(HttpServletRequest request, HttpServletResponse response,Long companyId){
		AUDJson result = new AUDJson();
		try {
			result = accountService.selectJoinConditionOne(companyId);
			
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setData(null);
		}	
		return result;
	}
	
	@RequestMapping("updateJoinCondition")
	@ResponseBody	
	public AUDJson updateJoinCondition(HttpServletRequest request, HttpServletResponse response,JoinCondition joinCondition){
		AUDJson result = new AUDJson();
		try {
			result = accountService.updateJoinCondition(joinCondition);
			
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("91征信  Exception: ", e);
			result.setResult(false);
			result.setMsg("系统异常！");
			result.setData(null);
		}	
		return result;
	}	
	
	/**
	 * 下载对接信息
	 * @param request
	 * @param response
	 * @param cpName
	 * @param bdName
	 * @param joinState
	 * @param startdate
	 * @param enddate
	 */
	@RequestMapping("deriveDatas")
	public void deriveDatas(HttpServletRequest request, HttpServletResponse response,@RequestParam(defaultValue = "0")String cpName
			,@RequestParam(defaultValue = "0")String bdName,@RequestParam(defaultValue = "-1")short joinState
			,@RequestParam(defaultValue = "0")long startdate,@RequestParam(defaultValue = "0")long enddate){
		try{
			List<JoinConditionExt> joinConditionList = accountService.deriveDatas(cpName, bdName, joinState, startdate, enddate);
			
			Workbook workbook = new Workbook();
			Worksheet sheet1 = workbook.getWorksheets().get(0);
			Cells cells = sheet1.getCells();
			cells.get(0, 0).putValue("公司名称");
			cells.get(0, 1).putValue("开始日期");
			cells.get(0, 2).putValue("结束日期");
			cells.get(0, 3).putValue("客户经理");
			cells.get(0, 4).putValue("对接状况");
			cells.get(0, 5).putValue("客户邮箱");
			cells.get(0, 6).putValue("是否开放被查询");
			cells.get(0, 7).putValue("实时信息");
			cells.get(0, 8).putValue("备注");
			for (int i = 0; i < joinConditionList.size(); i++) {
				cells.get(i+1, 0).putValue(joinConditionList.get(i).getCompanyName());
				if(joinConditionList.get(i).getStartTime()!=null){
//					cells.get(i+1, 1).putValue(StringUtils.getDateString(new Date(joinConditionList.get(i).getStartTime()), StringUtils.DATE_LINE_YMD));
				}
				if(joinConditionList.get(i).getEndTime()!=null){
//					cells.get(i+1, 2).putValue(StringUtils.getDateString(new Date(joinConditionList.get(i).getEndTime()), StringUtils.DATE_LINE_YMD));
				}
				cells.get(i+1, 3).putValue(joinConditionList.get(i).getBdName());
				
				if(joinConditionList.get(i).getJoinState()!=null){
					if(joinConditionList.get(i).getJoinState()==0){
							cells.get(i+1, 4).putValue("未开始");
					}if(joinConditionList.get(i).getJoinState()==1){
							cells.get(i+1, 4).putValue("排期");
					}if(joinConditionList.get(i).getJoinState()==2){
							cells.get(i+1, 4).putValue("对接中");
					}if(joinConditionList.get(i).getJoinState()==3){
							cells.get(i+1, 4).putValue("对接后暂停");
					}if(joinConditionList.get(i).getJoinState()==4){
							cells.get(i+1, 4).putValue("已完成");
					}if(joinConditionList.get(i).getJoinState()==5){
							cells.get(i+1, 4).putValue("放弃跟进");
					}
				}
				
				cells.get(i+1, 5).putValue(joinConditionList.get(i).getClientEmail());
				if(joinConditionList.get(i).getIsP3001()!=null){
					if(joinConditionList.get(i).getIsP3001()==0){
						cells.get(i+1, 6).putValue("不开放");
					}else if(joinConditionList.get(i).getIsP3001()==1){
						cells.get(i+1, 6).putValue("开放");
					}
				}
				cells.get(i+1, 7).putValue(joinConditionList.get(i).getTimeInformation());
				cells.get(i+1, 8).putValue(joinConditionList.get(i).getRemark());
			}
			response.setContentType("application/msexcel");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("对接信息表.xlsx", "UTF-8"));
            OutputStream os = response.getOutputStream();
            workbook.save(os, com.aspose.cells.SaveFormat.XLSX);
			
			os.flush();
			os.close();	 
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally{
			
		}
	}
}
