package com.xinmiao.back.domain;

import javax.persistence.*;

public class Apply {
    @Id
    @Column(name = "apply_id")
    private Integer applyId;

    /**
     * 申请人
     */
    @Column(name = "apply_user")
    private Integer applyUser;

    /**
     * 申请的投资机构
     */
    @Column(name = "apply_company_id")
    private String applyCompanyId;

    /**
     * 审核状态
     */
    @Column(name = "apply_status")
    private String applyStatus;

    @Column(name = "is_read")
    private Integer isRead;

    private String applyCompanyName;

    public String getApplyCompanyName() {
        return applyCompanyName;
    }

    public void setApplyCompanyName(String applyCompanyName) {
        this.applyCompanyName = applyCompanyName;
    }

    /**
     * @return apply_id
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * @param applyId
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取申请人
     *
     * @return apply_user - 申请人
     */
    public Integer getApplyUser() {
        return applyUser;
    }

    /**
     * 设置申请人
     *
     * @param applyUser 申请人
     */
    public void setApplyUser(Integer applyUser) {
        this.applyUser = applyUser;
    }

    /**
     * 获取申请的投资机构
     *
     * @return apply_company_id - 申请的投资机构
     */
    public String getApplyCompanyId() {
        return applyCompanyId;
    }

    /**
     * 设置申请的投资机构
     *
     * @param applyCompanyId 申请的投资机构
     */
    public void setApplyCompanyId(String applyCompanyId) {
        this.applyCompanyId = applyCompanyId == null ? null : applyCompanyId.trim();
    }

    /**
     * 获取审核状态
     *
     * @return apply_status - 审核状态
     */
    public String getApplyStatus() {
        return applyStatus;
    }

    /**
     * 设置审核状态
     *
     * @param applyStatus 审核状态
     */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus == null ? null : applyStatus.trim();
    }

    /**
     * @return is_read
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * @param isRead
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}