package com.zgxt.demo.service.impl;


import cn.hutool.core.util.HexUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zgxt.demo.model.Result;
import com.zgxt.demo.model.bo.TransactionBO;
import com.zgxt.demo.model.vo.ResultVO;
import com.zgxt.demo.service.ITransactionService;
import com.zgxt.demo.utils.IOUtil;
import com.zgxt.demo.utils.WeBASEUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    WeBASEUtils weBASEUtils;
    @Value("${system.contract.supplyChainFinAddress}")
    String contractAddress;

    public static final String ABI = IOUtil.readResourceAsString("abi/SupplyChainFin.abi");


    //TODO：任务3-2-5
    /**
     * 公司向银行发送凭证（银行转账给公司）
     * */
    public Result<String> bankToCompanyReceipt(TransactionBO transactionBO) {
        return commonProcess(transactionBO, "bankToCompanyReceipt");
    }

    //TODO：任务3-2-6
    /**
     * 公司向公司的凭证发送
     * */
    public Result<String> companyToCompanyReceipt(TransactionBO transactionBO) {
        return commonProcess(transactionBO, "companyToCompanyReceipt");
    }

    /**
     * 银行向公司发送凭证
     * */
    public Result<String> companyToBankReceipt(TransactionBO transactionBO) {
        return commonProcess(transactionBO,"companyToBankReceipt");
    }

    Result<String> commonProcess(TransactionBO transactionBO,String funcName) {

        List funcParam = new ArrayList();
        funcParam.add(transactionBO.getSenderAddress());
        funcParam.add(transactionBO.getAccepterAddress());
        funcParam.add(transactionBO.getAmount());
        funcParam.add(transactionBO.getReceiptType());

        String _result = weBASEUtils.funcPost(transactionBO.getAccepterAddress(),funcName,funcParam);

        JSONObject respBody = JSONUtil.parseObj(_result);
        String output = (String) respBody.get("output");
        long resInt = HexUtil.hexToLong(output.substring(2));
        if (resInt == 404001) {
            return Result.error(ResultVO.BANK_NOT_EXIST);
        }else if(resInt == 404002) {
            return Result.error(ResultVO.COMPANY_NOT_EXIST);
        }else if(resInt == 500001) {
            return Result.error(ResultVO.AMOUNT_NOT_ENOUGH);
        }else {
            return Result.success("ok");
        }
    }
}
