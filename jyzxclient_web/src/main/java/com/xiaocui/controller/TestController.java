package com.xiaocui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaocui.common.HttpClientUtils;
import com.xiaocui.dao.HeroLoL;
import com.xiaocui.dao.UseHeroDetail;

@Controller
@RequestMapping("test")
public class TestController {
	
	
	@RequestMapping("testSave")	
	public String testSave(HttpServletRequest request,HttpServletResponse resoonse){
		
		try {
			request.getServletContext().setAttribute("name", "翔哥");
			request.getServletContext().setAttribute("name", "洪翔");
			request.getServletContext().removeAttribute("name");
			
			System.out.println("执行方法。。。");
			System.out.println("执行方法完成。。。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("testRankV")	
	public String testLOLV(HttpServletRequest request,HttpServletResponse resoonse,String serverName,String playerName){
		String url = "http://API.xunjob.cn/rank.php?playerName="+playerName+"&serverName="+serverName;
		HttpGet get = new HttpGet(url);
		CloseableHttpClient httpclient = HttpClientUtils.getCloseableHttpClient();
		CloseableHttpResponse responses;
		String result = "";
		try {
			responses = httpclient.execute(get);
			HttpEntity rspEntity = responses.getEntity();
			JSONObject jsonResult = null;
			if (rspEntity != null) {
				result = EntityUtils.toString(rspEntity);
				jsonResult=new JSONObject(result);
				System.out.println(jsonResult);
			}
			if(jsonResult!=null){
				String returnValue = (String) jsonResult.get("message");
				if(returnValue.equals("1")){
					request.setAttribute("returnValue", true);
					String rank = (String) jsonResult.get("rank");
					String duanwei = (String) jsonResult.get("duanwei");
					String rank6 = (String) jsonResult.get("rank6");
					String matchstr = (String) jsonResult.get("matchstr");
					String name = (String) jsonResult.get("name");
					
					request.setAttribute("rank", rank);
					request.setAttribute("duanwei", duanwei);
					request.setAttribute("rank6", rank6);
					request.setAttribute("matchstr", matchstr);
					request.setAttribute("name", name);
				}else{
					request.setAttribute("returnValue", false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lolRank";
	}
	
	@RequestMapping("RecordV")	
	@ResponseBody
	public void RecordV(HttpServletRequest request,HttpServletResponse resoonse,String serverName,String playerName){
		String url = "http://API.xunjob.cn/Record.php?playerName="+playerName+"&serverName="+serverName;
   	 	HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        CloseableHttpClient httpclient = httpClientBuilder.build();  
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse responses = null;
		String result = "";
		JSONObject jsonResult = null;
		
		try {
			responses = httpclient.execute(get);
			HttpEntity rspEntity = responses.getEntity();
			if (rspEntity != null) {
				result = EntityUtils.toString(rspEntity);
				System.out.println(result);
				if(!"null".equals(result))
					jsonResult=new JSONObject(result);
					System.out.println(jsonResult);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("lolSearchV")
	public String lolSearchV(HttpServletRequest request,HttpServletResponse resoonse){
		request.setAttribute("message", false);
		return "lolSearch";
	}
	
	@RequestMapping("lolDetailSearch")	
	public String lolDetailSearch(HttpServletRequest request,HttpServletResponse resoonse,String serverName,String playerName){
		
		request.setAttribute("serverName", serverName);
		request.setAttribute("playerName", playerName);
		
		String urlPI = "http://API.xunjob.cn/playerinfo.php?playerName="+playerName+"&serverName="+serverName;
		String urlRecord="http://API.xunjob.cn/Record.php?playerName="+playerName+"&serverName="+serverName;
		String urlhero="http://API.xunjob.cn/hero.php?playerName="+playerName+"&serverName="+serverName;	
		String urls5str="http://API.xunjob.cn/s5str.php?playerName="+playerName+"&serverName="+serverName;	
		
		
   	 	HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        CloseableHttpClient httpclient = httpClientBuilder.build();  
		CloseableHttpResponse responses = null;
		String result = "";
		JSONObject jsonResult = null;
		
		try {
			//playerinfo
			HttpGet get = new HttpGet(urlPI);
			responses = httpclient.execute(get);
			HttpEntity rspEntity = responses.getEntity();
			if (rspEntity != null) {
				result = EntityUtils.toString(rspEntity);
				request.setAttribute("message", true);
				System.out.println(result);
				if(!"null".equals(result)&&result!=null){
					jsonResult=new JSONObject(result);
					System.out.println(jsonResult);
					String zhandouli = (String) jsonResult.get("zhandouli");
					String level = (String) jsonResult.get("level");
					String portrait = (String) jsonResult.get("portrait");
					String good = (String) jsonResult.get("good");
					
					request.setAttribute("zhandouli", zhandouli);
					request.setAttribute("level", level);
					request.setAttribute("portrait", portrait);
					request.setAttribute("good", good);
					
				}else{
					request.setAttribute("message", false);
				}
			}
			
			//record
			get = new HttpGet(urlRecord);
			responses = httpclient.execute(get);
			rspEntity = responses.getEntity();
			if (rspEntity != null) {
				result = EntityUtils.toString(rspEntity);
				if(!"null".equals(result)&&result!=null){

					jsonResult=new JSONObject(result);
					System.out.println(jsonResult);
					String Record = (String) jsonResult.get("Record");
					
					request.setAttribute("Record", Record);
				}
			}
			
			//hero
			get = new HttpGet(urlhero);
			responses = httpclient.execute(get);
			rspEntity = responses.getEntity();
			if (rspEntity != null) {
				result = EntityUtils.toString(rspEntity);
				if(!"null".equals(result)&&result!=null){

					jsonResult=new JSONObject(result);
					JSONArray heroList = (JSONArray)jsonResult.get("herostr");
					List<HeroLoL> heroLoLList = new ArrayList<HeroLoL>();
					if(heroList.length()>0){
						for (int i = 0; i < heroList.length(); i++) {
							HeroLoL heroLoL = new HeroLoL();
							JSONObject jsonResult1 =  new JSONObject(heroList.get(i).toString());
							JSONObject arrtObject = (JSONObject) jsonResult1.get("attr");
							String src = (String) arrtObject.get("src");
							String title = (String) arrtObject.get("title");
							heroLoL.setSrc(src);
							heroLoL.setTitle(title);
							heroLoLList.add(heroLoL);
						}
					}
					request.setAttribute("heroLoLList", heroLoLList);
				}
			}
			
			String urlindex="http://lolbox.duowan.com/new/api/index.php?_do=personal/championslist&serverName="+serverName+"&playerName="+playerName;	
			//urlindex
			get = new HttpGet(urlindex);
			responses = httpclient.execute(get);
			rspEntity = responses.getEntity();
			List<UseHeroDetail> UserHeroDetailList = new ArrayList<UseHeroDetail>();
			if (rspEntity != null) {
				result = EntityUtils.toString(rspEntity);
				if(!"null".equals(result)&&result!=null){
					jsonResult=new JSONObject(result);
					JSONArray contentArray = (JSONArray)jsonResult.get("content");
					for (int i = 0; i < contentArray.length(); i++) {
						UseHeroDetail useHeroDetail = new UseHeroDetail();
						JSONObject content =  new JSONObject(contentArray.get(i).toString());
						useHeroDetail.setChampionNameCN((String)content.get("championNameCN"));
						System.out.println((String)content.get("championNameCN"));
						useHeroDetail.setWinRate((Integer)content.get("winRate"));
						useHeroDetail.setMatchStat((Integer)content.get("matchStat"));
						
						JSONArray averageKDArray = (JSONArray)content.get("averageKDA");
						int[] a = new int[3];
						a[0] = (int) averageKDArray.get(0);
						a[1] = (int) averageKDArray.get(1);
						a[2] = (int) averageKDArray.get(2);
						useHeroDetail.setAverageKDA(a);
						
						useHeroDetail.setAverageKDARating((String)content.get("averageKDARating"));
						
						//场均输出
						JSONArray avKDARateArray = (JSONArray)content.get("averageDamage");
						String acKdaRate = avKDARateArray.get(0)+"."+avKDARateArray.get(1);
						useHeroDetail.setAverageDamage(acKdaRate);
						//场均分钟经济
						JSONArray averageEarnArray = (JSONArray)content.get("averageEarn");
						String averArray = averageEarnArray.get(0)+"."+averageEarnArray.get(1);
						useHeroDetail.setAverageEarn(averArray);

						useHeroDetail.setAverageMinionsKilled((Integer)content.get("averageMinionsKilled"));
						useHeroDetail.setTotalMVP((Integer)content.get("totalMVP"));
						
						UserHeroDetailList.add(useHeroDetail);
					}
				}
			}	
			
			request.setAttribute("UserHeroDetailList",UserHeroDetailList);
			
			
			
			//s5str
			get = new HttpGet(urls5str);
			responses = httpclient.execute(get);
			rspEntity = responses.getEntity();
			if (rspEntity != null) {
				result = EntityUtils.toString(rspEntity);
				request.setAttribute("tier", false);
				if(!"null".equals(result)&&result!=null&&!"{}".equals(result)){
					request.setAttribute("tier", true);
					jsonResult=new JSONObject(result);
					System.out.println(jsonResult);
					String tierrank = "";
					int league_points = 0;
					String warzone_updated = "";
					if (((Object)jsonResult.get("tier")).equals("null")) {	
						String tier = (String) jsonResult.get("tier");
						Object rank = jsonResult.get("rank");
						if(tier.equals("最强王者")){
							tierrank = tier;
						}else{
							tierrank = tier+(String)jsonResult.get("rank");
						}
						
						league_points = (int) jsonResult.get("league_points");
						warzone_updated = (String) jsonResult.get("warzone_updated");
					}
					request.setAttribute("tierrank", tierrank);
					request.setAttribute("league_points", league_points);
					request.setAttribute("warzone_updated", warzone_updated);
				}	
			}
			
			responses.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lolSearch";
	}
	
}
