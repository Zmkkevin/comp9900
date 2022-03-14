package com.project.comp9900.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.comp9900.domain.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results(@Result(column = "create_time", property = "createTime"))
    User findByName(String username);
}
