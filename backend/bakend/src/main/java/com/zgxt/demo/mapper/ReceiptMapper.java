package com.zgxt.demo.mapper;

import com.zgxt.demo.model.Receipt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiptMapper {
    void add(Receipt receipt);

}
