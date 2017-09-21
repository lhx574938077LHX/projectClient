package com.xiaocui.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaocui.common.HttpClientUtils;
import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.BaiduEntity;
import com.xiaocui.entity.BaiduRequest;
import com.xiaocui.entity.Result;

@Controller
@RequestMapping("blackList")
public class BlackListController {
	

	
	@RequestMapping("bdb")
	public String searchBaidu(HttpServletRequest request,@RequestParam(defaultValue="1")String realName,@RequestParam(defaultValue="1")String idCard) {
		request.setAttribute("isData", false);
		if(!"1".equals(realName)||!"1".equals(idCard)){
			BaiduRequest baiduRequest = new BaiduRequest();
			baiduRequest.setEventType("black_list");
//			baiduRequest.setSourceName("sourceNameTest");
			baiduRequest.setSourceName("91zhengxin");
			baiduRequest.setReqId(UUID.randomUUID().toString());
			baiduRequest.setSignType("1");
			baiduRequest.setSign("");
//			baiduRequest.setAgencyCode("");
//			baiduRequest.setTimestamp(DateUtils.dateToStr(new Date(),5));
			baiduRequest.setTimestamp(String.valueOf(new Date().getTime()));
			baiduRequest.setPrcid(idCard);
			baiduRequest.setName(realName);
			baiduRequest.setPhoneNumber("13614090708");
			
			String str = JsonSerializer.serializer(baiduRequest);

//			baiduRequest.setSign(DigestUtils.md5Hex(str +"verificationCode=verificationCodeTest"));
			baiduRequest.setSign(DigestUtils.md5Hex(str +"verificationCode=9d8b41cea7d4a24da61479c044c4a9b5"));
			str = JsonSerializer.serializer(baiduRequest);
			
			String url = "http://180.149.145.95:8010/credit/api";	//正式地址
			CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
			System.out.println("发送请求："+str);
			HttpPost post = new HttpPost(url);
			post.setEntity(new StringEntity(str,"utf-8"));
			
			CloseableHttpResponse response;
			String result = "";
			try {
				response = httpclient.execute(post);
				int retCode = response.getStatusLine().getStatusCode();
				if(retCode!=200)
					System.out.println("反馈请求状态码："+retCode);
				HttpEntity rspEntity = response.getEntity();

			    if (rspEntity != null) {
			    	result = EntityUtils.toString(rspEntity);
			    }
			    //输出返回信息
			    System.out.println(result);
			    BaiduEntity baidu = (BaiduEntity) JsonSerializer.deserializer(result,new TypeReference<BaiduEntity>(){});
			    request.setAttribute("result", baidu.getResult());
			    request.setAttribute("realName", realName);
			    request.setAttribute("idCard", idCard);
			    String reason = baidu.getResult().getBlackReason();
				request.setAttribute("isData", true);
			    //输出返回信息
			    System.out.println("成功结束！");
			    response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "baiduBlack";
	}
	
	public static void main(String[] args) {
		String path = "C:\\Users\\LHX\\Desktop\\war\\test4.xlsx";
		try {
			Workbook workbook = new Workbook(path);
			
			//读取第一个sheet
			WorksheetCollection worksheets = workbook.getWorksheets();
			Worksheet sheet = worksheets.get(0);
			Cells rows = sheet.getCells();
			
			Worksheet sheet1 = worksheets.add("百度黑名单");
			Cells cells = sheet1.getCells();
			cells.get(0,0).setValue("姓名");
			cells.get(0,1).setValue("身份证号");
			cells.get(0,2).setValue("是否命中");
			cells.get(0,3).setValue("风险等级");
			cells.get(0,4).setValue("加黑原因编码");
			cells.get(0,5).setValue("加黑原因");
			for (int j = 1; j < sheet.getCells().getRows().getCount(); j++) {
				Cell cellName = rows.get(j, 0);
				Cell cellIdcard = rows.get(j, 1);
//				Cell cellmobile = rows.get(j, 2);
				String realName = (String) cellName.getValue();
				String idCard = (String) cellIdcard.getValue();
//				String mobile = (String) cellmobile.getValue();
				BaiduRequest baiduRequest = new BaiduRequest();
				baiduRequest.setEventType("black_list");
				baiduRequest.setSourceName("91zhengxin");
				baiduRequest.setReqId(UUID.randomUUID().toString());
				baiduRequest.setSignType("1");
				baiduRequest.setSign("");
				baiduRequest.setAgencyCode("91zhengxin");
				baiduRequest.setTimestamp(String.valueOf(new Date().getTime()));
				baiduRequest.setPrcid(idCard);
				baiduRequest.setName(realName);
				baiduRequest.setPhoneNumber("13614090708");
//				baiduRequest.setPhoneNumber(mobile);
				
				String str = JsonSerializer.serializer(baiduRequest);
		
				baiduRequest.setSign(DigestUtils.md5Hex(str +"verificationCode=9d8b41cea7d4a24da61479c044c4a9b5"));
				str = JsonSerializer.serializer(baiduRequest);
				
				String url = "http://180.149.145.95:8010/credit/api";	//正式地址
//				String url = "http://180.149.144.184:8090/credit/api";	//测试地址
				CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
				System.out.println("发送请求："+str);
				HttpPost post = new HttpPost(url);
				post.setEntity(new StringEntity(str,"utf-8"));
				
				CloseableHttpResponse response;
				String result = "";
				response = httpclient.execute(post);
				int retCode = response.getStatusLine().getStatusCode();
				if(retCode!=200)
					System.out.println("反馈请求状态码："+retCode);
				HttpEntity rspEntity = response.getEntity();

			    if (rspEntity != null) {
			    	result = EntityUtils.toString(rspEntity);
			    }
			    //输出返回信息
			    System.out.println(realName+" + "+idCard+":"+result);
			    BaiduEntity baidu = (BaiduEntity) JsonSerializer.deserializer(result,new TypeReference<BaiduEntity>(){});
			    if("0".equals(baidu.getRetCode())){
				    Result rl = baidu.getResult();
				    cells.get(j,0).setValue(realName);
				    cells.get(j,1).setValue(idCard);
				    if(!"-9999".equals(rl.getBlackLevel())){
				    	cells.get(j,2).setValue("是");
				    	cells.get(j,3).setValue(rl.getBlackLevel());
				    	cells.get(j,4).setValue(rl.getBlackReason().replaceAll("\u0001", "-"));
				    	String reason = rl.getBlackReason().replace("T01","法人失信").replace("T02","法院失信").replace("T03","高危网络行为")
			    		.replace("T04","高危号码").replace("T05","身份伪冒风险").replace("T06","网贷失信").replace("T07","信贷欺诈").replace("T08","信贷逾期")
			    		.replace("T09","信用卡逾期").replace("T10","账号伪冒风险").replace("T11","支付欺诈").replace("T12","羊毛党")
			    		.replace("T13","在逃嫌犯").replace("T14","其他").replaceAll("\u0001", "-");
					    cells.get(j,5).setValue(reason);
					    
				    }else{
				    	cells.get(j,2).setValue("否");
				    }
			    }
			    response.close();
			}
			workbook.save(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
}
