package com.xinmiao.back.mapper;

import com.xinmiao.back.domain.SubComment;
import com.xinmiao.back.util.MyMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubCommentMapper extends MyMapper<SubComment> {
    @Select("select * from sub_comment where comment_id=#{commentId}")
    @ResultMap("BaseResultMap")
    public List<SubComment> selectSubCommentsByCommentId(Integer commentId);

    @Select("select * from sub_comment where user_b_name = #{bName}")
    @ResultMap("BaseResultMap")
    public List<SubComment> selectSubCommentsByBName(String bName);
}