package com.xinmiao.back.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

public class Comment {
    @Id
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "comment_user_id")
    private Integer commentUserId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_company_id")
    private Integer commentCompanyId;

    @Column(name = "coment_time")
    private Date comentTime;

    private String commentTimeStr;

    private String userIcon;
    private String userWx;

    public String getCommentTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(this.comentTime);
    }

    public void setCommentTimeStr(String commentTimeStr) {
        this.commentTimeStr = commentTimeStr;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserWx() {
        return userWx;
    }

    public void setUserWx(String userWx) {
        this.userWx = userWx;
    }

    /**
     * @return comment_id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * @param commentId
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * @return comment_user_id
     */
    public Integer getCommentUserId() {
        return commentUserId;
    }

    /**
     * @param commentUserId
     */
    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    /**
     * @return comment_content
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    /**
     * @return comment_company_id
     */
    public Integer getCommentCompanyId() {
        return commentCompanyId;
    }

    /**
     * @param commentCompanyId
     */
    public void setCommentCompanyId(Integer commentCompanyId) {
        this.commentCompanyId = commentCompanyId;
    }

    /**
     * @return coment_time
     */
    public Date getComentTime() {
        return comentTime;
    }

    /**
     * @param comentTime
     */
    public void setComentTime(Date comentTime) {
        this.comentTime = comentTime;
    }
}