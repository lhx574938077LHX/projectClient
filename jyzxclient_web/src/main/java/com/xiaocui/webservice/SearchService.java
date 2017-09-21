package com.xiaocui.webservice;

import java.util.List;

import javax.jws.WebService;

import com.xiaocui.entity.LoanInfo;

@WebService
public interface SearchService {
	
	List<LoanInfo> searchCustInfo(String realName,String idCard,String companyCode,String sign);
	
}
