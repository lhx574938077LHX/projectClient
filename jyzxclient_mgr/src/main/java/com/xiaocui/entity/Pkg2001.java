package com.xiaocui.entity;


public class Pkg2001 {
	private String trxNo;	//GUID
	
	public String getTrxNo() {
		return trxNo;
	}
	
	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}
	
	public static void main(String[] args) {
		Pkg2001 pkg = new Pkg2001();
		String str = "ABCDE";
		pkg.sort(str);
		System.out.println(str);
	}
	
	public void sort(String str){
		String s = str.replace("A", "E");
		s=s.toLowerCase();
		System.out.println(s);
	}
	
}

