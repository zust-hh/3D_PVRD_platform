var express = require('express');
var router = express.Router();
// 导入MySQL模块
var mysql = require('mysql');
var dbConfig = require('../db/DBConfig');
var company = require('../db/company');
var apply = require('../db/applysql');
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
router.get('/allcompanyinfo', function (req, res, next) {
    // 从连接池获取连接
    pool.getConnection(function (err, connection) {
        // 获取前台页面传过来的参数  
        var param = req.query || req.params;
        // 建立连接 增加一个用户信息 
        connection.query(company.queryAll, function (err, result) {
            if (result) {
                var calljson = {
                    code: 200,
                    msg: '请求成功',
                    arr: result
                };
            }
            // 以json形式，把操作结果返回给前台页面
            res.set('Content-Type', 'text/plain');
            responseJSON(res, calljson);

            // 释放连接  
            connection.release();

        });
    });
});
router.post('/companyapply', function (req, res, next) {
    var param = req.body;
    // 从连接池获取连接 
    pool.getConnection(function (err, connection) {
        // 建立连接 增加一个用户信息 
        connection.query(company.companyapply, [param.tag, param.id], function (err, result) {
            if (result) {
                result = {
                    result: result,
                    code: 200,
                    msg: '返回成功'
                };
            }

            // 以json形式，把操作结果返回给前台页面     
            responseJSON(res, result);

            // 释放连接  
            connection.release();

        });
    });
});
router.post('/companydelete', function (req, res, next) {
    var param = req.body;
    // 从连接池获取连接 
    pool.getConnection(function (err, connection) {
        // 建立连接 增加一个用户信息 
        connection.query(company.companydelete, [param.id], function (err, result) {
            if (result) {
                //查找所有的公司信息。
                connection.query(company.queryAll, function (err, result) {
                    if (result) {
                        var calljson = {};
                        calljson.code = 200;
                        calljson.msg = '请求成功';
                        calljson.arr = result;
                        responseJSON(res, calljson);
                    }
                });
            }

            // 以json形式，把操作结果返回给前台页面     


            // 释放连接  
            connection.release();

        });
    });
});
//查询入驻申请的公司
router.post('/searchInapplyCompany', function (req, res, next) {
    var param = req.body;
    var calljson = {};
    calljson.code = 200;
    calljson.msg = '请求成功';
    calljson.arr = [];
    // 从连接池获取连接 
    pool.getConnection(function (err, connection) {
        // 建立连接 增加一个用户信息 
        connection.query(apply.getInapplyByStatus, ['待审核'], function (err, result) {

            if (result) {
                //查找所有待审核的公司信息。
                for (var i = 0; i < result.length; i++) {
                    getresult(result, i);
                }

            }

            // 释放连接  
            connection.release();

        });
        function getresult(getresult, i) {
            connection.query(company.getCompanyByUserId, [getresult[i].in_apply_user_id], function (err, result) {
                calljson.arr.push(result[0])
                if (i == getresult.length - 1) {
                    responseJSON(res, calljson);
                }

            });
        }
    });
});
//agree or disagree 入驻申请
router.post('/updateInapplyStatus', function (req, res, next) {
    var param = req.body;
    var calljson = {};
    calljson.code = 200;
    calljson.msg = '请求成功';
    calljson.arr = [];
    console.log("进入", param)
    // 从连接池获取连接 
    pool.getConnection(function (err, connection) {
        // 建立连接 增加一个用户信息 
        connection.query(apply.uptadeStatus, [param.status, param.userId], function (err, result) {
            console.log(err)
            if (result) {
                responseJSON(res, calljson);
            }
            // 释放连接  
            connection.release();

        });

    });
});

//查询已审核的公司
router.post('/searchOverInapplyCompany', function (req, res, next) {
    var param = req.body;
    var calljson = {};
    calljson.code = 200;
    calljson.msg = '请求成功';
    calljson.arr = [];
    // 从连接池获取连接 
    pool.getConnection(function (err, connection) {
        // 建立连接 增加一个用户信息 
        connection.query(apply.getInapplyByOverStatus, ['已通过', '未通过'], function (err, result) {
            if (result) {
                //查找所有待审核的公司信息。
                for (var i = 0; i < result.length; i++) {
                    getresult(result, i);
                }

            }
            // 释放连接  
            connection.release();

        });
        function getresult(getresult, i) {
            connection.query(company.getCompanyByUserId, [getresult[i].in_apply_user_id], function (err, result) {
                if (result) {
                    try {
                        result[0].status = getresult[i].in_apply_status;
                        calljson.arr.push(result[0])
                        if (i == getresult.length - 1) {
                            responseJSON(res, calljson);
                        }
                    } catch (error) {

                    }

                }


            });
        }
    });
});
//删除申请入驻的公司
router.post('/delectInapply', function (req, res, next) {
    var param = req.body;
    var calljson = {};
    calljson.code = 200;
    calljson.msg = '请求成功';
    calljson.arr = [];
    // 从连接池获取连接 
    pool.getConnection(function (err, connection) {

        connection.query(apply.delectInapplyByUserId, [param.userId], function (err, result) {
            console.log(111, result, err)
            // 释放连接  
            connection.query(company.delectCompanyByUserId, [param.userId], function (err, result) {
                console.log(222, result, err)
                responseJSON(res, calljson);
                connection.release();
            });

            // connection.release();
        });


    });
});
module.exports = router;