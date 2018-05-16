package com.xinmiao.back.domain;

import javax.persistence.*;

public class Permission {
    /**
     * 权限id
     */
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * 权限名
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 权限url
     */
    @Column(name = "permission_url")
    private String permissionUrl;

    /**
     * 获取权限id
     *
     * @return permission_id - 权限id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限id
     *
     * @param permissionId 权限id
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取权限名
     *
     * @return permission_name - 权限名
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置权限名
     *
     * @param permissionName 权限名
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    /**
     * 获取权限url
     *
     * @return permission_url - 权限url
     */
    public String getPermissionUrl() {
        return permissionUrl;
    }

    /**
     * 设置权限url
     *
     * @param permissionUrl 权限url
     */
    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }
}