package com.xinmiao.back.mapper;


import com.xinmiao.back.domain.Permission;
import com.xinmiao.back.util.MyMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends MyMapper<Permission> {

    @Select("        SELECT pe.permission_id,pe.permission_name,pe.permission_url\n" +
            "        FROM permission pe LEFT JOIN role_permission rp\n" +
            "        ON pe.permission_id = rp.role_id\n" +
            "        LEFT JOIN user_role ur\n" +
            "        ON rr.roleId =ur.roleId\n" +
            "        WHERE ur.user_id=#{userId}\n" +
            "        GROUP BY pe.permission_id")
    @ResultMap("BaseResultMap")
    public List<Permission> loadUserPermissions(Integer userId);
}
