package com.zgxt.demo.service;



import com.zgxt.demo.model.Result;
import com.zgxt.demo.model.bo.LoginBO;
import com.zgxt.demo.model.bo.RegisterBO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Arthur
 * @since 2021-09-07
 */
public interface IOrgService {
    Result<String> login(@RequestBody LoginBO loginBO);
    Result<String> register(RegisterBO registerBO);
}
