package com.xinmiao.back.domain;

import javax.persistence.*;
import java.io.Serializable;

public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_passwd")
    private String userPasswd;

    @Column(name = "user_wx")
    private String userWx;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "token")
    private String token;

    @Column(name = "user_icon")
    private String userIcon;

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return user_passwd
     */
    public String getUserPasswd() {
        return userPasswd;
    }

    /**
     * @param userPasswd
     */
    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd == null ? null : userPasswd.trim();
    }

    /**
     * @return user_wx
     */
    public String getUserWx() {
        return userWx;
    }

    /**
     * @param userWx
     */
    public void setUserWx(String userWx) {
        this.userWx = userWx == null ? null : userWx.trim();
    }

    /**
     * @return user_type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}