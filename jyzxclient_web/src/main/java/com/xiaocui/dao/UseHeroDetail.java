package com.xiaocui.dao;

public class UseHeroDetail {
	private String championNameCN;//英雄名称
	private Integer averageMinionsKilled;//场均补兵
	private Integer winRate;//胜率
	private Integer matchStat;//使用场次
	private int[] averageKDA;//场均[杀/死/助]
	private String averageKDARating;//场均KDA
	private Integer totalMVP;//MVP次数
	private String averageDamage;//场均分钟输出
	private String averageEarn;//场均分钟经济
	
	public String getChampionNameCN() {
		return championNameCN;
	}
	public void setChampionNameCN(String championNameCN) {
		this.championNameCN = championNameCN;
	}
	public Integer getAverageMinionsKilled() {
		return averageMinionsKilled;
	}
	public void setAverageMinionsKilled(Integer averageMinionsKilled) {
		this.averageMinionsKilled = averageMinionsKilled;
	}
	public Integer getWinRate() {
		return winRate;
	}
	public void setWinRate(Integer winRate) {
		this.winRate = winRate;
	}
	public Integer getMatchStat() {
		return matchStat;
	}
	public void setMatchStat(Integer matchStat) {
		this.matchStat = matchStat;
	}
	public int[] getAverageKDA() {
		return averageKDA;
	}
	public void setAverageKDA(int[] averageKDA) {
		this.averageKDA = averageKDA;
	}
	public String getAverageKDARating() {
		return averageKDARating;
	}
	public void setAverageKDARating(String averageKDARating) {
		this.averageKDARating = averageKDARating;
	}
	public Integer getTotalMVP() {
		return totalMVP;
	}
	public void setTotalMVP(Integer totalMVP) {
		this.totalMVP = totalMVP;
	}
	public String getAverageDamage() {
		return averageDamage;
	}
	public void setAverageDamage(String averageDamage) {
		this.averageDamage = averageDamage;
	}
	public String getAverageEarn() {
		return averageEarn;
	}
	public void setAverageEarn(String averageEarn) {
		this.averageEarn = averageEarn;
	}
	
	
	
}
