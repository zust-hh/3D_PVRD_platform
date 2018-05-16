package com.xinmiao.back.mapper;

import com.xinmiao.back.domain.User;
import com.xinmiao.back.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends MyMapper<User> {
    @Select("select * from user where user_name = #{userName}")
    @ResultMap("BaseResultMap")
    public User selectUserByUserName(String userName);

    @Update("update user set token = #{token} where user_wx=#{wx}")
    public Integer updateTokenByWx(@Param("wx") String wx,@Param("token") String token);

    @Select("select * from user where user_wx = #{wx}")
    @ResultMap("BaseResultMap")
    public User selectByWx(@Param("wx") String wx);

}