package vip.sujianfeng.rbac;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.TypeReference;
import vip.sujianfeng.rbac.intf.*;
import vip.sujianfeng.rbac.utils.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * author SuJianFeng
 * createTime 2022/4/21
 **/
public class RbacHandler<U extends IUser, R extends IRole, P extends IPermission, RU extends IRoleUser, RP extends IRolePermission, M extends IMenu<M>> {

    private final static String RBAC_CACHE = "RBAC_CACHE";
    private final IDataLoader<U, R, P, RU, RP, M> dataCache;
    private final Class<U> uClass;
    private final Class<R> rClass;
    private final Class<P> pClass;
    private final Class<RU> ruClass;
    private final Class<RP> rpClass;
    private final Class<M> mClass;

    protected String getCacheKey() {
        return RBAC_CACHE;
    }

    public RbacHandler(Class<U> uClass, Class<R> rClass, Class<P> pClass, Class<RU> ruClass, Class<RP> rpClass, Class<M> mClass,
                       IDataLoader<U, R, P, RU, RP, M> dataCache) {
        this.dataCache = dataCache;
        this.uClass = uClass;
        this.rClass = rClass;
        this.pClass = pClass;
        this.ruClass = ruClass;
        this.rpClass = rpClass;
        this.mClass = mClass;
    }

    private RbacObject<U, R, P, RU, RP, M> parserRbacObject(String json)  {
        if (StringUtils.isEmpty(json)){
            return null;
        }
        Type type = new TypeReference<RbacObject<U, R, P, RU, RP, M>>(uClass, rClass, pClass, ruClass, rpClass, mClass) {}.getType();
        return JSON.parseObject(json, type);
    }

    public RbacObject<U, R, P, RU, RP, M> get()  {
        String json = this.dataCache.getCache(getCacheKey());
        RbacObject<U, R, P, RU, RP, M> obj = parserRbacObject(json);
        if (obj != null) {
            return obj;
        }
        obj = parserRbacObject("{}");
        assert obj != null;
        obj.loadData(dataCache);
        this.dataCache.putCache(getCacheKey(), JSON.toJSONString(obj));
        return obj;
    }

    public boolean has(IUser user, String permissionId)  {
        if (StringUtils.isEmpty(permissionId)){
            return true;
        }
        if (user.superAdminUser()) {
            return true;
        }
        String userId = String.valueOf(user.wgetId());
        List<IRoleUser> roleUsers = get().getRoleUsers().stream().filter(it -> it.wgetUserId().equals(userId)).collect(Collectors.toList());
        List<IRolePermission> rolePermissions = get().getRolePermissions().stream().filter(it -> it.wgetPermissionId().equals(permissionId)).collect(Collectors.toList());
        for (IRoleUser roleUser : roleUsers) {
            Optional<IRolePermission> find = rolePermissions.stream().filter(it -> it.wgetRoleId().equals(roleUser.wgetRoleId())).findFirst();
            if (find.isPresent()) {
                return true;
            }
        }
        return false;
    }

    public boolean has(Object userId, String permissionId) {
        if (StringUtils.isEmpty(permissionId)){
            return true;
        }
        Optional<U> findAny = this.get().getUsers().stream().filter(it -> String.valueOf(it.wgetId()).equalsIgnoreCase(String.valueOf(userId))).findAny();
        return findAny.isPresent() && has(findAny.get(), permissionId);
    }

    public List<String> getPermissions(Object userId) {
        List<String> result = new ArrayList<>();
        Optional<U> any = this.get().getUsers().stream().filter(it -> String.valueOf(it.wgetId()).equalsIgnoreCase(String.valueOf(userId))).findAny();
        if (any.isPresent() && any.get().superAdminUser()) {
            get().getPermissions().forEach(it -> result.add(it.wgetId()));
            return result;
        }
        List<IRoleUser> roleUsers = get().getRoleUsers().stream().filter(it -> it.wgetUserId().equalsIgnoreCase(String.valueOf(userId))).collect(Collectors.toList());
        for (IRoleUser roleUser : roleUsers) {
            List<String> collect = get().getRolePermissions().stream().filter(it -> it.wgetRoleId().equalsIgnoreCase(roleUser.wgetRoleId())).map(IRolePermission::wgetPermissionId).collect(Collectors.toList());
            result.addAll(collect);
        }
        return result;
    }


    public List<M> getMenuList(Object userId) {
        List<M> list = new ArrayList<>();
        for (M menuItem : this.get().getMenuItems()) {
            //非根菜单跳过
            if (!StringUtils.isEmpty(menuItem.wgetParentId())) {
                continue;
            }
            if (!hasMenuItem(userId, menuItem)) {
                continue;
            }
            this.addSubMenu(userId, menuItem);
            if (menuItem.wgetChildren() != null && menuItem.wgetChildren().size() > 0) {
                list.add(menuItem);
            }
        }
        return list;
    }

    public List<M> getMenuList() {
        List<M> list = new ArrayList<>();
        for (M menuItem : this.get().getMenuItems()) {
            //非根菜单跳过
            if (!StringUtils.isEmpty(menuItem.wgetParentId())) {
                continue;
            }
            this.addSubMenu(menuItem);
            if (menuItem.wgetChildren() != null && menuItem.wgetChildren().size() > 0) {
                list.add(menuItem);
            }
        }
        return list;
    }

    private int addSubMenu(Object userId, M menuItem) {
        if (!this.has(userId, menuItem.wgetPermissionId())){
            return 0;
        }
        int count = 0;
        List<M> subMenus = this.get().getMenuItems().stream().filter(it -> it.wgetParentId() != null && menuItem.wgetId().equalsIgnoreCase(it.wgetParentId())).collect(Collectors.toList());
        for (M subMenu : subMenus) {
            if (!hasMenuItem(userId, menuItem)) {
                continue;
            }
            count += this.addSubMenu(userId, subMenu);
            menuItem.wgetChildren().add(subMenu);
        }
        return count;
    }

    public boolean hasMenuItem(Object userId, M menuItem) {
        if (!StringUtils.isEmpty(menuItem.wgetPermissionId())) {
            //绑定权限
            return this.has(userId, menuItem.wgetPermissionId());
        }
        //菜单权限
        return this.has(userId, menuItem.wgetId());
    }

    private int addSubMenu(M menuItem) {
        int count = 0;
        List<M> subMenus = this.get().getMenuItems().stream().filter(it -> it.wgetParentId() != null && menuItem.wgetId().equalsIgnoreCase(it.wgetParentId())).collect(Collectors.toList());
        for (M subMenu : subMenus) {
            count += this.addSubMenu(subMenu);
            menuItem.wgetChildren().add(subMenu);
        }
        return count;
    }

    public void updateCache() {
        this.clearCache();
        this.get();
    }

    public void clearCache() {
        this.dataCache.clearCache(getCacheKey());
    }
}
