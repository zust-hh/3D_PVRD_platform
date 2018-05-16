package com.xinmiao.back.controller;

import com.xinmiao.back.domain.Comment;
import com.xinmiao.back.domain.Company;
import com.xinmiao.back.domain.SubComment;
import com.xinmiao.back.domain.User;
import com.xinmiao.back.mapper.CommentMapper;
import com.xinmiao.back.mapper.CompanyMapper;
import com.xinmiao.back.mapper.SubCommentMapper;
import com.xinmiao.back.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Resource
    CommentMapper commentMapper;
    @Resource
    SubCommentMapper subCommentMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    CompanyMapper companyMapper;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Boolean comment(@RequestBody CommentController comment){
        return false;
    }

    @RequestMapping(value = "/subComment",method = RequestMethod.POST)
    public Boolean subComment(@RequestBody SubComment subComment){
        return false;
    }


    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Map<String,Object>> getCommentByCompanyId(Integer id,String name){
        List<Comment> comments;
        if(id == null){
            User user = userMapper.selectByWx(name);
            Company company = companyMapper.selectByUserId(user.getUserId());
            comments = commentMapper.selectCommentsByCompanyId(company.getCompanyId());
        }else{
           comments = commentMapper.selectCommentsByCompanyId(id);
        }
        System.out.println("id = " + id);
        System.out.println(comments);

        List<Map<String,Object>> coms = new ArrayList<Map<String,Object>>();
        for(Comment comment : comments){
            Map<String,Object> subcoms = new HashMap<String,Object>();
            User user = userMapper.selectByPrimaryKey(comment.getCommentUserId());
            comment.setUserIcon(user.getUserIcon());
            comment.setUserWx(user.getUserWx());

            subcoms.put("comment",comment);
            subcoms.put("subcomments",subCommentMapper.selectSubCommentsByCommentId(comment.getCommentId()));
            coms.add(subcoms);
        }
        return coms;
    }


}
