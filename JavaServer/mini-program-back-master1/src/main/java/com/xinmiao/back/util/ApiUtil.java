package com.xinmiao.back.util;


import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;

@Slf4j
public class ApiUtil {

    public static Boolean recharge(BigDecimal number){
        System.out.println("充值成功");
        return true;
    }

    public static Boolean sendContentToTele(String content,String telephone){
        log.debug("send to " + telephone + ",content:" + content);
        return true;
    }

}
