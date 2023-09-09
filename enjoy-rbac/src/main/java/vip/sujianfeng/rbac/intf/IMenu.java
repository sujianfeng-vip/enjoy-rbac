package vip.sujianfeng.rbac.intf;

import java.util.List;

/**
 * author SuJianFeng
 * createTime 2022/5/9
 * description
 **/
public interface IMenu<M> extends IBase {

    String wgetParentId();

    String wgetPermissionId();

    List<M> wgetChildren();

    void addChild(M menu);
}
