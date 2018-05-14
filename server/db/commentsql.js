var commentsql = {
    //查询该公司下所有的评论
    getCommentsByCompanyId:'SELECT * FROM comment JOIN user  ON comment.comment_user_id = user.user_id',
    //通过主评论id挑选子评论
    getSubCommengsByCommentsId:'SELECT * FROM sub_comment where comment_id = ?',
    //提交主评论
    submitComment:'INSERT INTO comment(comment_user_id,comment_content,comment_company_id,coment_time) VALUES(?,?,?,?)',
    //提交子评论
    submitSubComment:'INSERT INTO sub_comment(comment_id,user_a_name,user_b_name,sub_comment_content,sub_comment_time) VALUES(?,?,?,?,?)',
}


module.exports = commentsql;