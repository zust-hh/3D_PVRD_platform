package com.xinmiao.back.service;

import com.xinmiao.back.domain.User;
import com.xinmiao.back.dto.RegisterUser;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    /**
     * 用户注册功能
     *
     * @param registerUser
     *      ：dto对象
     * @return
     *      :返回注册是否成功
     */
    public Boolean register(RegisterUser registerUser);

    /**
     * 根据用户id更新，若user中id为null，则更新失败，且只能更新以下字段：
     * 1.email
     * 2.delivery_address
     * 3.gender
     *
     * @param user
     *      ：domain对象
     * @return
     */
    public Boolean updateUserBaseInfoById(User user);

    /**
     * 用户充值，只能更新用户的余额字段
     * @param userId
     * @param number
     * @return
     */
    public Boolean rechargeById(Integer userId,BigDecimal number);


    /**
     * 用户提现，只能更新用户的余额字段
     * @param userId
     *      ：用户id
     * @param number
     *      ：充值金额
     * @return
     */
    public Boolean withdrawById(Integer userId,BigDecimal number);


}
