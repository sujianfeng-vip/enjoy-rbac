package vip.sujianfeng.rbac;

import vip.sujianfeng.rbac.intf.IDataLoader;
import vip.sujianfeng.rbac.models.*;

import java.util.List;

public class RbacObjectTest {

    public static void main(String[] args) {
        RbacSimple rbacSimple = new RbacSimple(
                new IDataLoader<BaseUser, BaseRole, BasePermission, BaseRoleUser, BaseRolePermisstion, BaseMenu>() {
                    @Override
                    public void putCache(String key, String value) {

                    }

                    @Override
                    public String getCache(String key) {
                        return null;
                    }

                    @Override
                    public void clearCache(String key) {

                    }

                    @Override
                    public List<BaseUser> loadUsers() {
                        return null;
                    }

                    @Override
                    public List<BaseRole> loadRoles() {
                        return null;
                    }

                    @Override
                    public List<BasePermission> loadPermissions() {
                        return null;
                    }

                    @Override
                    public List<BaseRoleUser> loadRoleUsers() {
                        return null;
                    }

                    @Override
                    public List<BaseRolePermisstion> loadRolePermissions() {
                        return null;
                    }

                    @Override
                    public List<BaseMenu> loadMenus() {
                        return null;
                    }

                }

        );


    }
}
