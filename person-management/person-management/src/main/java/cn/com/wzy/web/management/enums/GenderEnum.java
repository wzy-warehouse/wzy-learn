package cn.com.wzy.web.management.enums;

import cn.com.wzy.web.management.utils.I18nCacheUtils;

public enum GenderEnum {
    MALE(I18nCacheUtils.getMessage("user.gender.male.text")),
    FEMALE(I18nCacheUtils.getMessage("user.gender.female.text"));

    private String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
