package com.xinmiao.back.config.status;

public enum AuthorStatus {
    UNAUTHENTICATED(1001),  //未登录认证
    AUTH_LOST(1002),        //登录失效
    PERMISSION_DENIED(1003),//权限不够

    REGISTER_OK(200),      //注册成功
    REGISTER_FAILED_REPEATED_ACCOUNT(2002),  //注册失败，用户名重复

    SEND_VECODE_FAILED(3001),//发送短信验证码失败

    CONFIRM_CREDIT_CARD_FAILED(4001);   //实名银行卡失败

    private Integer code;
    AuthorStatus(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return this.code;
    }
}

