package com.xinmiao.back.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sub_comment")
public class SubComment {
    @Id
    @Column(name = "sub_comment_id")
    private Integer subCommentId;

    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "user_a_name")
    private String userAName;

    @Column(name = "user_b_name")
    private String userBName;

    @Column(name = "sub_comment_content")
    private String subCommentContent;

    @Column(name = "sub_comment_time")
    private Date subCommentTime;

    @Column(name = "is_read")
    private Integer isRead;

    /**
     * @return sub_comment_id
     */
    public Integer getSubCommentId() {
        return subCommentId;
    }

    /**
     * @param subCommentId
     */
    public void setSubCommentId(Integer subCommentId) {
        this.subCommentId = subCommentId;
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
     * @return user_a_name
     */
    public String getUserAName() {
        return userAName;
    }

    /**
     * @param userAName
     */
    public void setUserAName(String userAName) {
        this.userAName = userAName == null ? null : userAName.trim();
    }

    /**
     * @return user_b_name
     */
    public String getUserBName() {
        return userBName;
    }

    /**
     * @param userBName
     */
    public void setUserBName(String userBName) {
        this.userBName = userBName == null ? null : userBName.trim();
    }

    /**
     * @return sub_comment_content
     */
    public String getSubCommentContent() {
        return subCommentContent;
    }

    /**
     * @param subCommentContent
     */
    public void setSubCommentContent(String subCommentContent) {
        this.subCommentContent = subCommentContent == null ? null : subCommentContent.trim();
    }

    /**
     * @return sub_comment_time
     */
    public Date getSubCommentTime() {
        return subCommentTime;
    }

    /**
     * @param subCommentTime
     */
    public void setSubCommentTime(Date subCommentTime) {
        this.subCommentTime = subCommentTime;
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