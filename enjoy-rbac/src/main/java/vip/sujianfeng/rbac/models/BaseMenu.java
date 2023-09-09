package vip.sujianfeng.rbac.models;

import vip.sujianfeng.rbac.intf.IMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * author SuJianFeng
 * createTime 2022/5/11
 * description
 */
public class BaseMenu extends BaseInfo implements IMenu<BaseMenu> {

    private String parentId;
    private String PermissionId;
    private List<BaseMenu> children = new ArrayList<>();


    @Override
    public String wgetParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String wgetPermissionId() {
        return PermissionId;
    }

    @Override
    public void addChild(BaseMenu menu) {
        this.children.add(menu);
    }

    public void setPermissionId(String permissionId) {
        this.PermissionId = permissionId;
    }

    @Override
    public List<BaseMenu> wgetChildren() {
        return children;
    }

    public void setChildren(List<BaseMenu> children) {
        this.children = children;
    }

    public String getParentId() {
        return parentId;
    }

    public String getPermissionId() {
        return PermissionId;
    }

    public List<BaseMenu> getChildren() {
        return children;
    }
}
