package vip.sujianfeng.rbac.models;

import vip.sujianfeng.rbac.intf.IBase;

/**
 * author SuJianFeng
 * createTime 2022/5/11
 * description
 */
public class BaseInfo implements IBase {
    private String id;
    private String name;

    @Override
    public String wgetId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String wgetName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
