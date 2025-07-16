package cn.com.wzy.web.management.enums;

import cn.com.wzy.web.management.utils.I18nCacheUtils;

/**
 * 菜单类型枚举
 */
public enum MenuTypeEnum {
    M(I18nCacheUtils.getMessage("menu.type.m")),
    C(I18nCacheUtils.getMessage("menu.type.c")),
    F(I18nCacheUtils.getMessage("menu.type.f"));

    private String type;
    private MenuTypeEnum(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
