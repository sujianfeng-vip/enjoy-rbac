package vip.sujianfeng.rbac.models;

import vip.sujianfeng.rbac.intf.IUser;

/**
 * author SuJianFeng
 * createTime 2022/5/11
 * description
 */
public class BaseUser extends BaseInfo implements IUser {

    private int superUser;

    @Override
    public Boolean superAdminUser() {
        return superUser == 1;
    }

    public int getSuperUser() {
        return superUser;
    }

    public void setSuperUser(int superUser) {
        this.superUser = superUser;
    }

}
