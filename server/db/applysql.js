var applysql = {
  //入驻申请查询
    //查找未通过的入驻申请
    getInapplyByStatus:'SELECT * FROM in_apply where in_apply_status = ?',
    //改变审核状态
    uptadeStatus:'UPDATE in_apply SET in_apply_status = ? WHERE in_apply_user_id = ? ',
    //查询已审核的公司
    getInapplyByOverStatus:'SELECT * FROM in_apply where in_apply_status = ? OR in_apply_status = ?',
    //删除申请入驻的公司
    delectInapplyByUserId:'DELETE FROM in_apply where in_apply_user_id = ?',


  //申请投资机构查询

    //创业公司查询自己申请的投资机构
    getTouZistatusByUserId:'SELECT * FROM apply where apply_user_id = ?',

    //通过投资公司id拿到申请 创业公司
    getChuangYeByCompanyId:"SELECT * FROM apply where apply_company_id = ? and apply_status = '待审核'",

    updataApplyStatus:'UPDATE apply SET apply_status = ? WHERE apply_user_id = ? and apply_company_id = ?',

    //小程序端创业公司申请投资
    insertApplyTouZi:'INSERT INTO apply(apply_user_id,apply_company_id) VALUES(?,?)',

    //判断用户是否申请过该投资公司
    selectIfApplyTouZi:'SELECT * FROM apply where apply_company_id = ? and apply_user_id = ?',
  }
  module.exports = applysql;