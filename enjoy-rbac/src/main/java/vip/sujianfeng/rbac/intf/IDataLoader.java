package vip.sujianfeng.rbac.intf;

import java.util.List;

/**
 * author SuJianFeng
 * createTime 2022/5/9
 **/
public interface IDataLoader<U extends IUser, R extends IRole, P extends IPermission, RU extends IRoleUser, RP extends IRolePermission, M extends IMenu<M>> {
 
    void putCache(String key, String value);

    String getCache(String key);

    void clearCache(String key);

    List<U> loadUsers();
    List<R> loadRoles();
    List<P> loadPermissions();
    List<RU> loadRoleUsers();
    List<RP> loadRolePermissions();
    List<M> loadMenus();
}
