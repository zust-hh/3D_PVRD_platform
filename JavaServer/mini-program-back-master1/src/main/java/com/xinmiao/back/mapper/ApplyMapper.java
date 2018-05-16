package com.xinmiao.back.mapper;

import com.xinmiao.back.domain.Apply;
import com.xinmiao.back.util.MyMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApplyMapper extends MyMapper<Apply> {

    @Select("select cu.company_name from company as cu,apply,user,company where cu.user_id = apply.apply_user and user.user_wx = #{wx} and company.user_id=user.user_id and apply.apply_company_id=company.company_id")
    public List<String> selectBy2Name(String wx);
}