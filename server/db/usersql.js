var usersql = {  
  insert:'INSERT INTO users(id,username,password,name) VALUES(?,?,?,?)', 
  queryAll:'SELECT * FROM users',  
  getUserById:'SELECT * FROM users WHERE id = ?',
};
module.exports = usersql;
