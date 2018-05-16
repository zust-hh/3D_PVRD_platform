package com.xinmiao.back.mapper;

import com.xinmiao.back.domain.Comment;
import com.xinmiao.back.util.MyMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends MyMapper<Comment> {
    @Select("select * from comment where comment_company_id = #{companyId}")
    @ResultMap("BaseResultMap")
    public List<Comment> selectCommentsByCompanyId(Integer companyId);
}