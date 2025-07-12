package cn.com.wzy.web.management.enums;

public enum GenderEnum {
    MALE("男"),
    FEMALE("女");

    private String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
