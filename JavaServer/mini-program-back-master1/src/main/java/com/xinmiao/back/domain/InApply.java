package com.xinmiao.back.domain;

import javax.persistence.*;

@Table(name = "in_apply")
public class InApply {
    @Id
    @Column(name = "in_apply_id")
    private Integer inApplyId;

    @Column(name = "in_apply_user_id")
    private Integer inApplyUserId;

    @Column(name = "in_apply_status")
    private String inApplyStatus;

    /**
     * @return in_apply_id
     */
    public Integer getInApplyId() {
        return inApplyId;
    }

    /**
     * @param inApplyId
     */
    public void setInApplyId(Integer inApplyId) {
        this.inApplyId = inApplyId;
    }

    /**
     * @return in_apply_user_id
     */
    public Integer getInApplyUserId() {
        return inApplyUserId;
    }

    /**
     * @param inApplyUserId
     */
    public void setInApplyUserId(Integer inApplyUserId) {
        this.inApplyUserId = inApplyUserId;
    }

    /**
     * @return in_apply_status
     */
    public String getInApplyStatus() {
        return inApplyStatus;
    }

    /**
     * @param inApplyStatus
     */
    public void setInApplyStatus(String inApplyStatus) {
        this.inApplyStatus = inApplyStatus == null ? null : inApplyStatus.trim();
    }
}