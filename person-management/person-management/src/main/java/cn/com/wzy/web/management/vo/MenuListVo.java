package cn.com.wzy.web.management.vo;

import java.util.List;
import java.util.Objects;

/**
 * @author wzy
 * @description: TODO(返回给前端的MenuList对象)
 * @date 2025/7/15 上午11:46
 */
public class MenuListVo {
    private Long id;
    private String name;
    private String path;
    private String component;
    private String menuName;
    private String icon;
    private List<MenuListVo> children;

    public MenuListVo() {
        super();
    }

    public MenuListVo(Long id, String name, String path, String component, String menuName, String icon, List<MenuListVo> children) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.component = component;
        this.menuName = menuName;
        this.icon = icon;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuListVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuListVo> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuListVo that = (MenuListVo) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(path, that.path) && Objects.equals(component, that.component) && Objects.equals(menuName, that.menuName) && Objects.equals(icon, that.icon) && Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path, component, menuName, icon, children);
    }

    @Override
    public String toString() {
        return "MenuListVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", menuName='" + menuName + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }
}