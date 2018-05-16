package com.xinmiao.back.controller;



import com.xinmiao.back.config.shiro.ExceptionHandledController;
import com.xinmiao.back.config.shiro.realm.UsernamePasswordLoginTypeToken;
import com.xinmiao.back.config.status.AuthorStatus;
import com.xinmiao.back.domain.Company;
import com.xinmiao.back.domain.User;
import com.xinmiao.back.dto.LoginToken;
import com.xinmiao.back.dto.RespJson;
import com.xinmiao.back.mapper.CompanyMapper;
import com.xinmiao.back.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/sessions")
public class SessionController extends ExceptionHandledController {
    @Resource
    UserMapper userMapper;
    @Resource
    CompanyMapper companyMapper;

    //登录
    @PostMapping("")
    public RespJson login(@RequestBody LoginToken loginToken){
        RespJson respJson = new RespJson();
        Subject subject = SecurityUtils.getSubject();
//        System.out.println("tele = " + loginToken.getTelephoneNumber()+" passwd = " + loginToken.getPasswd());
        try {
            User user = userMapper.selectUserByUserName(loginToken.getTelephoneNumber());
            int isWxlogin = 0;
            if (user == null){
                isWxlogin = 1;
                user = userMapper.selectByWx(loginToken.getTelephoneNumber());
                //如果是微信号登录
                if(user == null){
                    //如果没有这个用户，插入
                    user = new User();
                    user.setUserWx(loginToken.getTelephoneNumber());
                    user.setUserPasswd("");
                    user.setUserIcon(loginToken.getUserIcon());
                    user.setUserType("1");
                    userMapper.insertSelective(user);
                }
            }
            User putUser = new User();
            putUser.setUserId(user.getUserId());
            putUser.setUserIcon(loginToken.getUserIcon());
            userMapper.updateByPrimaryKeySelective(putUser);
            UsernamePasswordLoginTypeToken token = new UsernamePasswordLoginTypeToken(loginToken.getTelephoneNumber(), loginToken.getPasswd(),loginToken.getLoginType());
            subject.login(token);
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("token",subject.getSession().getId());
            User I = userMapper.selectByWx(user.getUserWx());
            data.put("user",I);
            respJson.setData(data);
            respJson.setMsg("登录成功");
            respJson.setCode(200);
        } catch (IncorrectCredentialsException e) {
            respJson.setMsg("密码错误");
        } catch (LockedAccountException e) {
            respJson.setMsg("登录失败，该账户被冻结");
        } catch (AuthenticationException e) {
            respJson.setMsg("用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respJson;
    }

//    @RequiresUser
//    @RequestMapping(value = "/sessions/{id}",method = RequestMethod.DELETE)
//    public RespJson logout(@PathVariable String id){
//        Subject subject = SecurityUtils.getSubject();
//        RespJson respJson = new RespJson(null,null,1001);
//        Session session = subject.getSession(false);
//        if(session == null){
//            respJson.setMsg("用户未登录");
//            return respJson;
//        }
//
//        if(session.getId().equals(id)){
//            subject.logout();
//            respJson.setMsg("注销成功");
//            respJson.setCode(200);
//            return respJson;
//        }else {
//            respJson.setMsg("错误的id");
//            return respJson;
//        }
//    }



    @RequestMapping(value = "/authLose",method = RequestMethod.GET)
    public RespJson authLose() {
        RespJson respJson = new RespJson(null,AuthorStatus.AUTH_LOST.name(),AuthorStatus.AUTH_LOST.getCode());//登录失效
        return respJson;
    }

    /**
     *当在shiroConfig中配置的权限不够时，会跳转到这里，当注解中权限不够时，会跳转到上面两个异常捕捉器
     * @return
     */
    @RequestMapping(value = "/unauth",method = RequestMethod.GET)
    public RespJson unauth() {
        RespJson respJson = new RespJson(null, AuthorStatus.UNAUTHENTICATED.name(),AuthorStatus.UNAUTHENTICATED.getCode());//未登录用户
        return respJson;
    }





    //    @RequiresUser
//    @RequiresPermissions("/usersPage")
//    @RequestMapping(value = "/testget",method = RequestMethod.GET)
//    public RespJson test(){
//        //Subject subject = SecurityUtils.getSubject();
//        //System.out.println("sessionId = "+subject.getSession().getId());
//        //User user = (User)subject.getSession().getAttribute("userSession");
//        //subject.getSession().setAttribute("test",tid);
//
//        //if(subject.getSession(false) == null){
//        //  return new RespJson("ok","test ok",200);
//        //}
//        return new RespJson("ok","test ok",200);
//    }
//
//    @RequiresUser
//    @RequestMapping(value = "/testput",method = RequestMethod.GET)
//    public RespJson test(@RequestParam Integer tid){
//        Subject subject = SecurityUtils.getSubject();
//        System.out.println("sessionId = "+subject.getSession().getId());
//        User user = (User)subject.getSession().getAttribute("userSession");
//        subject.getSession().setAttribute("test",tid);
//        return new RespJson(subject.getSession().getAttribute("test"),"test ok",200);
//    }
}