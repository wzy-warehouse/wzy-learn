package cn.com.wzy.web.management.entity;

import cn.com.wzy.web.management.enums.MenuTypeEnum;

import java.util.Objects;

/**
 * @author wzy
 * @description: TODO(菜单实体)
 * @date 2025/7/15 上午10:57
 */
public class Menu extends BaseEntity{
    private Long menuId;           // 菜单ID
    private String menuName;       // 菜单名称（JSON字符串）
    private Long parentId;         // 父菜单ID
    private Integer orderNum;      // 显示顺序
    private String path;           // 路由地址
    private String component;      // 组件路径
    private String query;          // 路由参数（JSON字符串）
    private String routeName;      // 路由名称
    private Boolean isFrame;       // 是否为外链
    private MenuTypeEnum menuType; // 菜单类型
    private Boolean visible;       // 显示状态
    private Boolean status;        // 状态（1正常/0停用）
    private String perms;          // 权限标识
    private String icon;           // 菜单图标

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Boolean getFrame() {
        return isFrame;
    }

    public void setFrame(Boolean frame) {
        isFrame = frame;
    }

    public MenuTypeEnum getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuTypeEnum menuType) {
        this.menuType = menuType;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return Objects.equals(menuId, menu.menuId) && Objects.equals(menuName, menu.menuName) && Objects.equals(parentId, menu.parentId) && Objects.equals(orderNum, menu.orderNum) && Objects.equals(path, menu.path) && Objects.equals(component, menu.component) && Objects.equals(query, menu.query) && Objects.equals(routeName, menu.routeName) && Objects.equals(isFrame, menu.isFrame) && Objects.equals(menuType, menu.menuType) && Objects.equals(visible, menu.visible) && Objects.equals(status, menu.status) && Objects.equals(perms, menu.perms) && Objects.equals(icon, menu.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), menuId, menuName, parentId, orderNum, path, component, query, routeName, isFrame, menuType, visible, status, perms, icon);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", parentId=" + parentId +
                ", orderNum=" + orderNum +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", query='" + query + '\'' +
                ", routeName='" + routeName + '\'' +
                ", isFrame=" + isFrame +
                ", menuType='" + menuType + '\'' +
                ", visible=" + visible +
                ", status=" + status +
                ", perms='" + perms + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}