package com.xinmiao.back.config.shiro.realm;

public enum SupportedLoginType {
    TelephoneAndPasswd(1),
    TelephoneAndToken(2),
    TelephoneAndVerifyCode(3);

    private Integer code;
    SupportedLoginType(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return this.code;
    }
}
