package com.xinmiao.back.controller;

import com.xinmiao.back.config.shiro.ExceptionHandledController;
import com.xinmiao.back.dto.RespJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
public class TestController extends ExceptionHandledController {
//    @RequiresRoles("admin")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RespJson testget(){
        Subject subject = SecurityUtils.getSubject();

        return new RespJson(subject.getSession().getHost(),"test ok",200);
    }
}
