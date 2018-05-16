package com.xinmiao.back.mapper;

import com.xinmiao.back.domain.Company;
import com.xinmiao.back.util.MyMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyMapper extends MyMapper<Company> {
    @Select("select company.* from company,in_apply,user where user.user_id = in_apply.in_apply_user_id and user.user_id=company.user_id and in_apply.in_apply_status='已通过' and company_type = #{type}")
    @ResultMap("BaseResultMap")
    public List<Company> selectCompanys(Integer type);

    @Select("select company.* from company,in_apply,user where user.user_id = in_apply.in_apply_user_id and user.user_id=company.user_id and in_apply.in_apply_status='已通过' and company.user_id = #{userId}")
    @ResultMap("BaseResultMap")
    public Company selectByUserId(Integer userId);
}