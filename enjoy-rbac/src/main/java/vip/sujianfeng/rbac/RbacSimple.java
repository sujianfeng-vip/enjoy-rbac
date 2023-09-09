package vip.sujianfeng.rbac;

import vip.sujianfeng.rbac.intf.IDataLoader;
import vip.sujianfeng.rbac.models.*;

/**
 * author SuJianFeng
 * createTime 2022/5/11
 */
public class RbacSimple extends RbacHandler<BaseUser, BaseRole, BasePermission, BaseRoleUser, BaseRolePermisstion, BaseMenu> {
    public RbacSimple(IDataLoader<BaseUser, BaseRole, BasePermission, BaseRoleUser, BaseRolePermisstion, BaseMenu> dataCache) {
        super(BaseUser.class, BaseRole.class, BasePermission.class, BaseRoleUser.class, BaseRolePermisstion.class, BaseMenu.class, dataCache);
    }
}
