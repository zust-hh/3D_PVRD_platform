var usersql = {  
  insert:'INSERT INTO users(id,username,password,name) VALUES(?,?,?,?)', 
  queryAll:'SELECT * FROM users',  
  getUserById:'SELECT * FROM users WHERE id = ?',
};
var companysql = {  
  insert:'INSERT INTO company(id,username,password,name) VALUES(?,?,?,?)', 
  queryAll:'SELECT * FROM company',  
  getUserById:'SELECT * FROM company WHERE id = ?',
  //公司申请,同意为1，拒绝为2
  companyapply:'UPDATE company SET company_tags = ? WHERE company_id = ? ',
  //删除公司
  companydelete:'DELETE FROM company WHERE company_id = ? ',
};
module.exports = companysql;
