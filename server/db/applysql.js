var applysql = {
    //查找未通过的入驻申请
    getInapplyByStatus:'SELECT * FROM in_apply where in_apply_status = ?',
    //改变审核状态
    uptadeStatus:'UPDATE in_apply SET in_apply_status = ? WHERE in_apply_user_id = ? ',
    //查询已审核的公司
    getInapplyByOverStatus:'SELECT * FROM in_apply where in_apply_status = ? OR in_apply_status = ?',
    //删除申请入驻的公司
    delectInapplyByUserId:'DELETE FROM in_apply where in_apply_user_id = ?'
  }
  module.exports = applysql;