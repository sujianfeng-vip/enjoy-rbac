package vip.sujianfeng.rbac.models;

import vip.sujianfeng.rbac.intf.IRoleUser;

/**
 * author SuJianFeng
 * createTime 2022/5/11
 * description
 */
public class BaseRoleUser implements IRoleUser {
    private String roleId;
    private String userId;

    @Override
    public String wgetRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String wgetUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getUserId() {
        return userId;
    }
}
