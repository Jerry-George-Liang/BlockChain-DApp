package com.zgxt.demo.mapper;

import com.zgxt.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrgMapper {
    void add(User user);

}
