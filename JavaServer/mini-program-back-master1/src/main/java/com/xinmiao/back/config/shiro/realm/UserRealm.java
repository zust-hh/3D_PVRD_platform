package com.xinmiao.back.config.shiro.realm;

import com.xinmiao.back.domain.User;
import com.xinmiao.back.util.PasswordHelper;
import org.apache.shiro.authc.*;
import org.apache.shiro.util.ByteSource;

public class UserRealm extends MyShiroRealm {
    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("userRealm  被调用");
        //获取用户的输入的账号.
        //log.debug("登录时候的认证～～～"+"username = " + String.valueOf(token.getPrincipal()) + ", pass = " + new String((char[])token.getCredentials()));
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user = userMapper.selectUserByUserName(username);
        int isWxlogin = 0;
        if (password.equals("")){
            isWxlogin = 1;
            user = userMapper.selectByWx(username);
            //如果是微信号登录
            if(user == null){
//                //如果没有这个用户，插入
//                user = new User();
//                user.setUserWx(username);
//                user.setUserPasswd("");
//                userMapper.insertSelective(user);
            }
        }
        String pass;

        if(isWxlogin != 1){
            System.out.println(user+"被调用");
            pass = user.getUserPasswd();
        }else{
            pass = password;
        }
        System.out.println("pass" + pass);
        SimpleAuthenticationInfo authenticationInfo;
        authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                pass, //正确密码
                ByteSource.Util.bytes(username),
                getName()  //realm name
        );

        //如果登录成功
        updateUserAndClearOldUserCache(user);
        return authenticationInfo;
    }

}
