package vip.sujianfeng.rbac.models;

import vip.sujianfeng.rbac.intf.IRolePermission;

/**
 * author SuJianFeng
 * createTime 2022/5/11
 * description
 */
public class BaseRolePermisstion implements IRolePermission {
    private String roleId;
    private String permissionId;

    @Override
    public String wgetRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String wgetPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }
}
