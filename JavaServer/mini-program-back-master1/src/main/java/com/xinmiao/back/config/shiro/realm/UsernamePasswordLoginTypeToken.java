package com.xinmiao.back.config.shiro.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordLoginTypeToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 2162137019102435135L;
    /**
     *登陆类型
     */
    private Integer loginType;

    public UsernamePasswordLoginTypeToken(String username, String password, Integer loginType){
        super(username,password);
        this.loginType = loginType;
    }
    public UsernamePasswordLoginTypeToken(String username, String password, boolean rememberMe, String host, Integer loginType) {
        super(username, password, rememberMe, host);
        this.loginType = loginType;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }
}