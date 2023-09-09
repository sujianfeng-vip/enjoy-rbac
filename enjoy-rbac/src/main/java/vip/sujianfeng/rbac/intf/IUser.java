package vip.sujianfeng.rbac.intf;

import java.io.Serializable;

/**
 * author SuJianFeng
 * createTime 2022/4/21
 * description
 **/
public interface IUser extends Serializable {
    Object wgetId();
    String wgetName();
    Boolean superAdminUser();
}
