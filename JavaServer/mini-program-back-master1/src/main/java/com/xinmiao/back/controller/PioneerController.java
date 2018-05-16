package com.xinmiao.back.controller;

import com.xinmiao.back.domain.Comment;
import com.xinmiao.back.domain.Company;
import com.xinmiao.back.domain.Img;
import com.xinmiao.back.mapper.CommentMapper;
import com.xinmiao.back.mapper.CompanyMapper;
import com.xinmiao.back.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/pioneer")
public class PioneerController {
    @Resource
    CommentMapper commentMapper;
    @Resource
    CompanyMapper companyMapper;
    @Resource
    UserMapper userMapper;

    @GetMapping("/getInvestList")
    public List<Company> investCompanyList(){
        return companyMapper.selectCompanys(2);
    }
    @GetMapping("/getInvestDetail")
    public Company investCompany(Integer id,String name){
        if(id == null){
            Integer userId = userMapper.selectByWx(name).getUserId();
            return companyMapper.selectByUserId(userId);
        }
        return companyMapper.selectByPrimaryKey(id);
    }

    //获取所有公司
    @GetMapping("/getPioneerList")
    public List<Company> pioneerCompanyList(){
        return companyMapper.selectCompanys(1);
    }

    //获取公司详情
    @GetMapping("/getPioneerDetail")
    public Company pioneerCompany(Integer id,String name){
        if(id == null){
            Integer userId = userMapper.selectByWx(name).getUserId();
            System.out.println(userId);
            return companyMapper.selectByUserId(userId);
        }
        return companyMapper.selectByPrimaryKey(id);
    }

    //获取公司评论
    @GetMapping("/getPioneerComment")
    public List<Comment> pioneerComment(Integer id){
        return commentMapper.selectCommentsByCompanyId(id);
    }

    @GetMapping("/getPioneerImgs")
    public List<Img> pioneerImgs(Integer id){
        return null;
    }



}



