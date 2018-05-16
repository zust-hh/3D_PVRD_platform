package com.xinmiao.back.controller;


import com.xinmiao.back.config.shiro.ExceptionHandledController;
import com.xinmiao.back.config.status.AuthorStatus;
import com.xinmiao.back.domain.User;
import com.xinmiao.back.dto.RegisterUser;
import com.xinmiao.back.dto.RespJson;
import com.xinmiao.back.mapper.UserMapper;
import com.xinmiao.back.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController extends ExceptionHandledController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public RespJson register(@RequestBody RegisterUser registerUser){
        RespJson respJson = new RespJson(null,"",200);
        if(userService.register(registerUser)){
            Subject subject = SecurityUtils.getSubject();
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("token",subject.getSession().getId());
            respJson.setData(data);
            respJson.setMsg(AuthorStatus.REGISTER_OK.name());
            respJson.setCode(AuthorStatus.REGISTER_OK.getCode());
        }else{
            respJson.setMsg(AuthorStatus.REGISTER_FAILED_REPEATED_ACCOUNT.name());
            respJson.setCode(AuthorStatus.REGISTER_FAILED_REPEATED_ACCOUNT.getCode());
        }
        return new RespJson("","register ok",200);
    }
}
