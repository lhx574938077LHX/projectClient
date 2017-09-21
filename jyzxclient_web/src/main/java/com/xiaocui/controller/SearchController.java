package com.xiaocui.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.xiaocui.entity.KeyGenerater;
import com.xiaocui.entity.PkgSecurity;
import com.xiaocui.entity.RSAUtils;
import com.xiaocui.platform.dao.data.JyzxJdxxcxfkMapper;
import com.xiaocui.platform.dao.search.CompanyInfoMapper;
import com.xiaocui.platform.model.data.JyzxJdxxcxfk;
import com.xiaocui.platform.model.search.CompanyInfo;
import com.xiaocui.service.SearchServiceImpl;
import com.xiaocui.thread.SearchTask;

@Controller
@RequestMapping("search")
public class SearchController {
	
	public static int reClients = 0;
	
	@Autowired
	private SearchServiceImpl search3002Service;
	
	@Autowired
	private JyzxJdxxcxfkMapper jyzxJdxxcxfkMapper;
	
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	
//	private String companyCode = "P2P4HJK0000100010"; 
	private String companyCode = "P2P4HJK0000101090"; 
	
//	@Autowired
//	private SearchTask searchTaskThread;
	
	@RequestMapping("searchCompany")
	@ResponseBody	
	public void searchCompany(HttpServletRequest request, HttpServletResponse response){
		String path = "‪C:\\Users\\李洪翔\\Desktop\\company.xlsx";
		try {
			Workbook workbook = new Workbook(path);
			
			//读取第一个sheet
			WorksheetCollection worksheets = workbook.getWorksheets();
			Worksheet sheet = worksheets.get(0);
			Cells cells = sheet.getCells();
			
			Map<String, String> paramMap = new HashMap<String,String>();  
			List<CompanyInfo> companyInfoList = companyInfoMapper.selectList(paramMap);
			long count = companyInfoMapper.selectCount(null);
			System.out.println(count);
			cells.get(0, 0).setValue("机构名称");
			cells.get(0, 1).setValue("机构代码");
			cells.get(0, 2).setValue("URL");
			cells.get(0, 3).setValue("签名");
			cells.get(0, 4).setValue("P00001");
			cells.get(0, 5).setValue("P00002");
			cells.get(0, 6).setValue("P00003");
			cells.get(0, 7).setValue("P00004");
			cells.get(0, 8).setValue("P00005");
			for(int i=0;i<count;i++){
				CompanyInfo companyInfo = companyInfoList.get(i);
				cells.get(i+1, 0).setValue(companyInfo.getCompanyName());
				cells.get(i+1, 1).setValue(companyInfo.getCompanyCode());
				cells.get(i+1, 2).setValue(companyInfo.getServiceUrl());
				cells.get(i+1, 3).setValue(companyInfo.getConnSign());
				cells.get(i+1, 4).setValue(companyInfo.getP00001());
				cells.get(i+1, 5).setValue(companyInfo.getP00002());
				cells.get(i+1, 6).setValue(companyInfo.getP00003());
				cells.get(i+1, 7).setValue(companyInfo.getP00004());
				cells.get(i+1, 8).setValue(companyInfo.getP00005());
			}
			workbook.save(path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 生成excel
	 * @param request
	 * @param response
	 */
	@RequestMapping("searchDatas")
	@ResponseBody
	public void searchDatas(HttpServletRequest request, HttpServletResponse response){
		
		long nowTime1 = System.currentTimeMillis();
		
		search3002Service.deleteJyzxJdxxcxfk(); //删除剩余数据
		
		String path = "C:\\Users\\LHX\\Desktop\\91征信接口开发文件\\文档\\58金融测试数据.xlsx";
		int clients = 0; //提交客户量
		int reInfors = 0;//反馈信息量
		try {
			Workbook workbook = new Workbook(path);
			
			//读取第一个sheet
			WorksheetCollection worksheets = workbook.getWorksheets();
			Worksheet sheet = worksheets.get(0);
			Cells rows = sheet.getCells();
			
			//新建一个sheet
			Worksheet sheet1 = worksheets.add("数据反馈-91征信");
			Cells cells = sheet1.getCells();
			cells.get(4, 0).setValue("编号");
			cells.get(4, 1).setValue("姓名");
			cells.get(4, 2).setValue("身份证");
			cells.get(4, 3).setValue("数据反馈时间");
			cells.get(4, 4).setValue("借款类型");
			cells.get(4, 5).setValue("借款状态");
			cells.get(4, 6).setValue("借款年月");
			cells.get(4, 7).setValue("借款金额");
			cells.get(4, 8).setValue("借款期数");
			cells.get(4, 9).setValue("当前状态");
			cells.get(4, 10).setValue("欠款金额");
			cells.get(4, 11).setValue("数据反馈方");
			int i = 5;
			
//			int cpuNums = Runtime.getRuntime().availableProcessors();
//	        System.out.println("当前CPU数目："+cpuNums);
			ExecutorService pool = Executors.newFixedThreadPool(4);  
			for (int j = 0; j < sheet.getCells().getRows().getCount(); j++) {
				clients = clients + 1;
				System.out.println("查询到第"+clients +"行");
				Cell cellName = rows.get(j, 0);
				Cell cellIdcard = rows.get(j, 1);
				String realName = (String) cellName.getValue();
				String idCard = (String) cellIdcard.getValue();
				System.out.println(realName+"------"+idCard);
				//方法1：将线程 用@Autowired注入,线程里就可以调用数据库了 2：new 线程，将mapper当做参数传入线程中
				SearchTask searchTaskThread = new SearchTask();
				searchTaskThread.setCompanyCode(companyCode);
				searchTaskThread.setIdCard(idCard);
				searchTaskThread.setRealName(realName);
				searchTaskThread.setJyzxJdxxcxfkMapper(jyzxJdxxcxfkMapper);
				pool.execute(new Thread(searchTaskThread));
				
//				Future<?> f = pool.submit(new Thread(searchTaskThread));
//				f.get();//如果Executor后台线程池还没有完成Callable的计算，这调用返回Future对象的get()方法，会阻塞直到计算完成。
			}	
			
			pool.shutdown(); 
	        while (true) {  

	            if (pool.isTerminated()) {  
	                break;  
	            }  
	        }
			
			
			List<JyzxJdxxcxfk> jyzxJdxxcxfks = search3002Service.searchJyzxJdxxcxfk();
			for(JyzxJdxxcxfk jyzxJdxxcxfk:jyzxJdxxcxfks){
				reInfors = reInfors  +1;
				cells.get(i, 0).setValue(i-4);//编号
				cells.get(i, 1).setValue(jyzxJdxxcxfk.getRealName());//姓名
				cells.get(i, 2).setValue(jyzxJdxxcxfk.getIdcard());//身份证号
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowDate = sdf.format(new Date(jyzxJdxxcxfk.getRecTime()));
				cells.get(i, 3).setValue(nowDate);//数据反馈时间
				
				if(jyzxJdxxcxfk.getBorrowtYpe()!=null){
					switch (jyzxJdxxcxfk.getBorrowtYpe()) {
						case 1:
							cells.get(i, 4).setValue("个人信贷");//借款类型
							break;
						case 2:
							cells.get(i, 4).setValue("个人抵押");//借款类型
							break;
						case 3:
							cells.get(i, 4).setValue("企业信贷");//借款类型
							break;
						case 4:
							cells.get(i, 4).setValue("企业抵押");//借款类型
							break;
						default:
							cells.get(i, 4).setValue("未知");//借款类型
							break;
					}
				}else{
					cells.get(i, 4).setValue("-");//借款类型
				}
				if(jyzxJdxxcxfk.getBorrowState()!=null){
					switch (jyzxJdxxcxfk.getBorrowState()) {
						case 1:
							cells.get(i, 5).setValue("拒贷");//借款状态
							break;
						case 2:
							cells.get(i, 5).setValue("批贷已放款");//借款状态
							break;
						case 4:
							cells.get(i, 5).setValue("借款人放弃申请");//借款状态
							break;
						case 5:
							cells.get(i, 5).setValue("审核中");//借款状态
							break;
						case 6:
							cells.get(i, 5).setValue("待放款");//借款状态
							break;
						default:
							cells.get(i, 5).setValue("未知");//借款状态
							break;
					}
				}else{
					cells.get(i, 5).setValue("-");//借款状态
				}
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
				Object contractStr = sdf1.format(jyzxJdxxcxfk.getContractDate());
				cells.get(i, 6).setValue(contractStr);//借款年月
				
				if(jyzxJdxxcxfk.getBorrowAmount()!=null){
					switch (jyzxJdxxcxfk.getBorrowAmount()) {
						case 1:
							cells.get(i, 7).setValue("【10000-20000)");//借款金额
							break;
						case 0:
							cells.get(i, 7).setValue("-");//借款金额
							break;
						case -1:
							cells.get(i, 7).setValue("【8000-10000)");//借款金额
							break;
						case -2:
							cells.get(i, 7).setValue("【6000-8000)");//借款金额
							break;
						case -3:
							cells.get(i, 7).setValue("【4000-6000)");//借款金额
							break;
						case -4:
							cells.get(i, 7).setValue("【3000-4000)");//借款金额
							break;
						case -5:
							cells.get(i, 7).setValue("【2000-3000)");//借款金额
							break;
						case -6:
							cells.get(i, 7).setValue("【1000-2000)");//借款金额
							break;
						case -7:
							cells.get(i, 7).setValue("【1-1000)");//借款金额
							break;
						default:
							int reBorrowAmount = (int)jyzxJdxxcxfk.getBorrowAmount();
							cells.get(i, 7).setValue("【"+(reBorrowAmount*2-2)+"0000-"+(reBorrowAmount*2)+"0000)");//借款金额
							break;
					}	
				}else{
					cells.get(i, 7).setValue("-");//借款金额
				}
				
				if(jyzxJdxxcxfk.getLoanPeriod()!=null){
					cells.get(i, 8).setValue(jyzxJdxxcxfk.getLoanPeriod());//借款期数
				}else{
					cells.get(i, 8).setValue("-");//借款期数
				}
				
				if(jyzxJdxxcxfk.getRepayState()!=null){
					switch (jyzxJdxxcxfk.getRepayState()) {
						case 0:
							cells.get(i, 9).setValue("-");//当前状态
							break;
						case 1:
							cells.get(i, 9).setValue("正常");//当前状态
							break;
						case 2:
							cells.get(i, 9).setValue("M1");//当前状态
							break;
						case 3:
							cells.get(i, 9).setValue("M2");//当前状态
							break;
						case 4:
							cells.get(i, 9).setValue("M3");//当前状态
							break;
						case 5:
							cells.get(i, 9).setValue("M4");//当前状态
							break;
						case 6:
							cells.get(i, 9).setValue("M5");//当前状态
							break;
						case 7:
							cells.get(i, 9).setValue("M6");//当前状态
							break;
						case 8:
							cells.get(i, 9).setValue("M6+");//当前状态
							break;
						case 9:
							cells.get(i, 9).setValue("已还清");//当前状态
							break;
						default:
							cells.get(i, 5).setValue("-");//当前状态
							break;						
					}
				}else{
					cells.get(i, 5).setValue("-");//当前状态
				}
				
				if(jyzxJdxxcxfk.getArrearsAmount()>0){
					BigDecimal arrearsDecimal = new BigDecimal(jyzxJdxxcxfk.getArrearsAmount());
					double arrearsAmount = arrearsDecimal.divide(new BigDecimal(100000)).doubleValue();
					cells.get(i, 10).setValue(arrearsAmount);
				}else{
					cells.get(i, 10).setValue("-");
				}
				cells.get(i, 11).setValue(jyzxJdxxcxfk.getCompanyCode());//反馈方
				if(jyzxJdxxcxfk.getBorrowState()==1||jyzxJdxxcxfk.getBorrowState()==4||jyzxJdxxcxfk.getBorrowState()==5){
					cells.get(i, 9).setValue("-");//当前状态
					cells.get(i, 7).setValue("-");//借款金额
					cells.get(i, 10).setValue("-");//欠款金额
					cells.get(i, 8).setValue("-");//借款期数
				}
				i = i+1;
			}
			
			cells.get(0, 0).setValue("提交客户量");
			cells.get(1, 0).setValue("反馈客户量");
			cells.get(2, 0).setValue("反馈信息量");
			cells.get(0, 1).setValue(clients);
			cells.get(1, 1).setValue(SearchController.reClients);
			cells.get(2, 1).setValue(reInfors);
			
			workbook.save(path);
			
			long nowTime2 = System.currentTimeMillis();
			System.out.println(nowTime2-nowTime1);
 			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		
	}
	
	@RequestMapping("sendTestSecurity")
	@ResponseBody
	public void sendTestSecurity(HttpServletRequest request, HttpServletResponse response) throws Exception{
		StringBuilder sb = new StringBuilder();    
		ServletInputStream is = null;
		OutputStream os = null;
		String line = null; 
		String publicKey = readFile("D:\\workspace\\workspace2\\example\\src\\main\\java\\cer\\filePulbicKey.cer");
		String privateKey = readFile("D:\\workspace\\workspace2\\example\\src\\main\\java\\cer\\filePrivateKey.jks");
		
		try {
			is = request.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }  
			String result = sb.toString();
			String[] strs = result.split("\\|");
			
			String data = strs[0];
			String companyCode = strs[1];
			String signs = strs[2];
			
			//取出数据、解密 
			byte[] dataByte = KeyGenerater.decryptBASE64(data);
			byte[] dataByte1 = RSAUtils.decryLongData(dataByte, publicKey);
			String dataStr = new String(dataByte1);
			
			//验证签名
			boolean legal = RSAUtils.verify(KeyGenerater.decryptBASE64(data), publicKey, signs);
			
			System.out.println("服务端："+dataStr);
			System.out.println("服务端："+legal);
			System.out.println("服务端："+companyCode);
			
			JSONObject resultJson = new JSONObject(dataStr);
			String idCard = (String) resultJson.get("idCard");
			String name = (String) resultJson.get("name");
			System.out.println("服务端："+name);
			System.out.println("服务端："+idCard);
			String datas;
			if(legal){
				datas = changeStr(name,idCard,true,"M3");
			}else{
				datas = "反馈签名有问题！";
			}
			//私钥加密
			byte[] datasByte = RSAUtils.encryLongData(datas.getBytes(), privateKey);
			//设置签名
			String sign = RSAUtils.sign(datasByte, privateKey);
			//加密信息
			String dataBase = KeyGenerater.encryptBASE64(datasByte);
			PkgSecurity pkgSecurity = new PkgSecurity();
			pkgSecurity.setData(dataBase);
			pkgSecurity.setSign(sign);
			pkgSecurity.setCompanyCode("P21T2CF1103364286");
			String returnStr = pkgSecurity.toString();
			
			//添加反馈信息
			os = response.getOutputStream();
			os.write(returnStr.getBytes("UTF-8"));
			
		    os.flush();	
		    os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
				if(is!=null){try {is.close();} catch (IOException e) {e.printStackTrace();}}
				if(os!=null){try {os.close();} catch (IOException e) {e.printStackTrace();}}
		}
	}
	
	
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
	
	public String changeStr(String name,String idCard,boolean legal,String repayState){
		
		StringBuffer sb = new StringBuffer();
		sb.append("{")
		.append("\"idCard\":")
		.append("\"" + idCard + "\",")
		.append("\"name\":")
		.append("\"" + name + "\",")
		.append("\"isBlack\":")
		.append("" + legal + ",")
		.append("\"repayDate\":")
		.append("\"" + repayState + "\"")
		.append("}");
		return sb.toString();
		
	}
	
	@RequestMapping("selectCount")
	@ResponseBody
	public void selectCount(HttpServletRequest request, HttpServletResponse response){
		String path = "C:\\Users\\LHX\\Desktop\\91征信接口开发文件\\51信用卡数据.xlsx";
		Workbook workbook;
		try {
			workbook = new Workbook(path);
			//读取第一个sheet
			WorksheetCollection worksheets = workbook.getWorksheets();
			Worksheet sheet = worksheets.get(0);
			Cells rows = sheet.getCells();
			int dataCount = 0;
			String newIdCard = "";
			for (int j = 1; j < sheet.getCells().getRows().getCount(); j++) {
				Cell cellIdcard = rows.get(j, 2);
				String idCard = (String) cellIdcard.getValue();
				if(!newIdCard.equals(idCard)){
					dataCount = dataCount + 1;
					newIdCard = idCard;
				}
				System.out.println(dataCount);
			}	
			System.out.println(dataCount);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}	
	
	@RequestMapping("setDataExcel")
	@ResponseBody
	public void setDataExcel(HttpServletRequest request, HttpServletResponse response){
		File file = new File("C:\\Users\\LHX\\Desktop\\测试1\\第二批测试数据.txt");
		BufferedReader reader= null;
		Workbook workbook;
		String path = "C:\\Users\\LHX\\Desktop\\91征信接口开发文件\\第二批测试数据.xlsx";
        try {
//        	 FileInputStream fr = new FileInputStream("C:\\Users\\LHX\\Desktop\\测试1\\测试1.txt");
//     	    InputStreamReader is = new InputStreamReader(fr,"GBK");
        	reader = new BufferedReader( new InputStreamReader( new FileInputStream(file), "GBK") ); 
//        	 reader = new BufferedReader(new FileReader(file));
        	workbook = new Workbook(path);
			//读取第一个sheet
			WorksheetCollection worksheets = workbook.getWorksheets();
			Worksheet sheet = worksheets.get(0);
			Cells rows = sheet.getCells();
        	
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                System.out.println("line " + line + ": " + tempString);
                String[] strs = tempString.split(" ");
                rows.get(line, 0).setValue(strs[0]);
                rows.get(line, 1).setValue(strs[1]);
                line++;
            }
            workbook.save(path);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}
}
