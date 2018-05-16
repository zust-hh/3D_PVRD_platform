package com.xinmiao.back.service.impl;

import com.xinmiao.back.config.shiro.realm.SupportedLoginType;
import com.xinmiao.back.config.shiro.realm.UsernamePasswordLoginTypeToken;
import com.xinmiao.back.domain.User;
import com.xinmiao.back.dto.RegisterUser;
import com.xinmiao.back.mapper.UserMapper;
import com.xinmiao.back.service.UserService;
import com.xinmiao.back.util.PasswordHelper;
import com.xinmiao.back.util.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtils redisUtils;


    public Boolean registerOnWx(String wx){
        User user = new User();
        user.setUserWx(wx);
        UsernamePasswordLoginTypeToken token = new UsernamePasswordLoginTypeToken(user.getUserWx(),"",SupportedLoginType.TelephoneAndPasswd.getCode());
        return true;
    }

    public Boolean register(RegisterUser registerUser) {
        if(registerUser.getWx() == null){
            return false;
        }

        User existUser = userMapper.selectByWx(registerUser.getWx());

        if(existUser != null && !existUser.getUserName().equals("")){
            return false;

        }

        User insertUser = new User();
        insertUser.setUserWx(registerUser.getWx());
        insertUser.setUserName(registerUser.getUserName());
        insertUser.setUserPasswd(registerUser.getPasswd());
        insertUser.setUserType(registerUser.getUserType());

        if(existUser != null){
            insertUser.setUserId(existUser.getUserId());
            userMapper.updateByPrimaryKeySelective(insertUser);
        }else {
            userMapper.insertSelective(insertUser);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordLoginTypeToken token = new UsernamePasswordLoginTypeToken(insertUser.getUserName(),insertUser.getUserPasswd(),SupportedLoginType.TelephoneAndPasswd.getCode());
        return true;
    }

    @Override
    public Boolean updateUserBaseInfoById(User user) {
        return null;
    }

    @Override
    public Boolean rechargeById(Integer userId, BigDecimal number) {
        return null;
    }

    @Override
    public Boolean withdrawById(Integer userId, BigDecimal number) {
        return null;
    }
}
