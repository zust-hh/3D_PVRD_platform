package com.xinmiao.back.config.shiro.realm;

import com.xinmiao.back.domain.User;
import com.xinmiao.back.util.PasswordHelper;
import org.apache.shiro.authc.*;
import org.apache.shiro.util.ByteSource;

public class TokenRealm extends MyShiroRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("tokenRealm  被调用");

        String username = (String)token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        User user = userMapper.selectByWx(username);

        if(user==null) throw new UnknownAccountException();

        SimpleAuthenticationInfo authenticationInfo;
        authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getToken(), //使用密码作为登录凭据
                ByteSource.Util.bytes(username),
                getName()  //realm name
        );

        String encryptPassword = PasswordHelper.encryptPassword(password,username);
//        String encryptPassword = password;
        //如果登录成功
        if(encryptPassword.equals(user.getToken())){
            updateUserAndClearOldUserCache(user);
        }
        return authenticationInfo;
    }
}
