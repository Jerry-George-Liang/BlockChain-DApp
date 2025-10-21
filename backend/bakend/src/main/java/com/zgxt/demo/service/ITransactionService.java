package com.zgxt.demo.service;


import com.zgxt.demo.model.Result;
import com.zgxt.demo.model.bo.TransactionBO;

public interface ITransactionService {
    Result<String> bankToCompanyReceipt(TransactionBO transactionBO);
    Result<String> companyToCompanyReceipt(TransactionBO transactionBO);
    Result<String> companyToBankReceipt(TransactionBO transactionBO);
}
