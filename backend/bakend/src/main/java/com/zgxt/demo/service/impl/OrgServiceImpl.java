package com.zgxt.demo.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.zgxt.demo.mapper.OrgMapper;
import com.zgxt.demo.model.Result;
import com.zgxt.demo.model.User;
import com.zgxt.demo.model.bo.LoginBO;
import com.zgxt.demo.model.bo.RegisterBO;
import com.zgxt.demo.model.vo.ResultVO;
import com.zgxt.demo.service.IOrgService;
import com.zgxt.demo.utils.IOUtil;
import com.zgxt.demo.utils.WeBASEUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;



/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Arthur
 * @since 2021-09-07
 */
@Service
public class OrgServiceImpl implements IOrgService {
    @Autowired
    WeBASEUtils weBASEUtils;

    @Value("${system.contract.owner_address}")
    private  String OWNER_ADDRESS;

    @Autowired
    private OrgMapper orgMapper;
    public static final String ABI = IOUtil.readResourceAsString("abi/SupplyChainFin.abi");

    //TODO：任务3-2-2
    /**
     * 登录Service
     * LoginBO loginBO
     **/
    @Override
    public Result<String> login(@RequestBody LoginBO loginBO) {
        if (StrUtil.isEmpty(loginBO.getAddress())
        ) {
            return Result.error(ResultVO.PARAM_EMPTY);
        }

        List funcParam = new ArrayList();
        funcParam.add(loginBO.getAddress());

        String funcName;
        if(loginBO.getOrgType() == 2){
            funcName ="getBank";
        }else{
            funcName ="getCompany";
        }

        String _result = weBASEUtils.funcPost(loginBO.getAddress(),funcName,funcParam);
        JSONArray _resultJson = JSONUtil.parseArray(_result);
        if (StrUtil.isEmpty(_resultJson.get(0).toString())){
            return Result.error(ResultVO.CONTRACT_ERROR);
        }

        return Result.success("ok");
    }


    //TODO：任务3-2-1
    /**
     * 注册Service
     * RegisterBO registerBO
     * */
    @Override
    public Result<String> register(RegisterBO registerBO) {
        if (StrUtil.isEmpty(registerBO.getUsername()) ||
                StrUtil.isEmpty(registerBO.getAddress()) ||
                registerBO.getOrgType() == null
        ) {
            return Result.error(ResultVO.PARAM_EMPTY);
        }

        List funcParam = new ArrayList();
        funcParam.add(registerBO.getUsername());
        funcParam.add(registerBO.getAddress());
        if(registerBO.getOrgType() == 2){
            funcParam.add(BigInteger.valueOf(1000));
        }

        String funcName;
        if(registerBO.getOrgType() == 2){
            funcName ="addBank";
        }else{
            funcName ="addCompany";
        }

        String _result = weBASEUtils.funcPost(OWNER_ADDRESS,funcName,funcParam);
        JSONObject _resultJson = JSONUtil.parseObj(_result);
        if (_resultJson.containsKey("statusOK") == false || _resultJson.getBool("statusOK") != true){ // _resultJson.getInt("code") > 0
            return Result.error(ResultVO.CONTRACT_ERROR);
        }
        //添加数据库
        User user = new User();
        user.setAddress(registerBO.getAddress());
        user.setOrgType(registerBO.getOrgType());
        user.setUsername(registerBO.getUsername());
        orgMapper.add(user);
        return Result.success("ok");
    }


//    public Result<String> rebind(String username, String address) {
////        UpdateWrapper uw = new UpdateWrapper();
////        uw.eq("username", username);
////        uw.set("account_address", address);
////        update(uw);
//        return Result.success("ok");
//    }




}
