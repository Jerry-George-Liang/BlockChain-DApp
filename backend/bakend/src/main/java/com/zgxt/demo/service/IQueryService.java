package com.zgxt.demo.service;



import cn.hutool.json.JSONArray;
import com.zgxt.demo.model.Result;
import com.zgxt.demo.model.vo.BankVO;
import com.zgxt.demo.model.vo.CompanyEntityVO;
import com.zgxt.demo.model.vo.CompanyVO;
import com.zgxt.demo.model.vo.ReceiptVO;

import java.util.List;

public interface IQueryService {

    Result listAllReceipt(String userAddress);
    List<ReceiptVO> listReceiptByIndex(JSONArray index, String userAddress);
    ReceiptVO getReceiptDetail(Integer index, String userAddress);
    Result listCompany(String userAddress);
    Result listBank(String userAddress);
    Result<CompanyEntityVO> getCompanyEntity(String userAddress, String queryAddress);
    Result getBankEntity(String userAddress, String queryAddress);
    CompanyVO getCompanyDetail(String userAddress, String queryAddress);
    BankVO getBankDetail(String userAddress, String queryAddress);
}
