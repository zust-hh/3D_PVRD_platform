var express = require('express');
var router = express.Router();
// 导入MySQL模块
var mysql = require('mysql');
var dbConfig = require('../db/DBConfig');
var usersql = require('../db/company');
var commentsql = require('../db/commentsql');
// 使用DBConfig.js的配置信息创建一个MySQL连接池
var pool = mysql.createPool(dbConfig.mysql);
// 响应一个JSON数据
var responseJSON = function (res, ret) {
  if (typeof ret === 'undefined') {
    res.json({
      code: '-200', msg: '操作失败'
    });
  } else {
    res.json(ret);
  }
};
router.post('/getComments', function (req, res, next) {
    var param = req.body;
    var calljson = {};
    calljson.code = 200;
    calljson.msg = '请求成功';
    calljson.arr = [];
    // 从连接池获取连接 
    pool.getConnection(function (err, connection) {
      // 建立连接 增加一个用户信息 
      connection.query(commentsql.getCommentsByCompanyId, [param.companyId], function (err, result) {
        for(var i=0;i<result.length;i++){
            console.log(i,result[i])
            // getSubComment(result,i);
        }
  
        // 以json形式，把操作结果返回给前台页面     
        // responseJSON(res, calljson);
  
        // 释放连接  
        connection.release();
  
      });
    });

    function getSubComment(firstResult,i){
        connection.query(commentsql,result.comment_id,function(err,result){
            firstResult[i].subComment = result;
            calljson.arr.push(firstResult);
            if(i == firstResult.length - 1){
                responseJSON(res, calljson);
                connection.release();
            }
        });
    }
  });

  module.exports = router;