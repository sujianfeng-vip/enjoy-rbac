package vip.sujianfeng.rbac;

import vip.sujianfeng.rbac.intf.*;

import java.util.ArrayList;
import java.util.List;

/**
 * author SuJianFeng
 * createTime 2022/4/21
 * description
 **/
public class RbacObject<U extends IUser, R extends IRole, P extends IPermission, RU extends IRoleUser, RP extends IRolePermission, M extends IMenu<M>> {

    public void loadData(IDataLoader<U, R, P, RU, RP, M> dataCache) {
        this.users = dataCache.loadUsers();
        this.roles = dataCache.loadRoles();
        this.permissions = dataCache.loadPermissions();
        this.roleUsers = dataCache.loadRoleUsers();
        this.rolePermissions = dataCache.loadRolePermissions();
        this.menuItems = dataCache.loadMenus();
    }

    private List<U> users = new ArrayList<>();
    private List<R> roles = new ArrayList<>();
    private List<P> permissions = new ArrayList<>();
    private List<RU> roleUsers = new ArrayList<>();
    private List<RP> rolePermissions = new ArrayList<>();
    private List<M> menuItems = new ArrayList<>();

    public List<U> getUsers() {
        return users;
    }

    public void setUsers(List<U> users) {
        this.users = users;
    }

    public List<R> getRoles() {
        return roles;
    }

    public void setRoles(List<R> roles) {
        this.roles = roles;
    }

    public List<P> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<P> permissions) {
        this.permissions = permissions;
    }

    public List<RU> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(List<RU> roleUsers) {
        this.roleUsers = roleUsers;
    }

    public List<RP> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RP> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public List<M> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<M> menuItems) {
        this.menuItems = menuItems;
    }
}
