package cn.com.wzy.web.management.entity;

import cn.com.wzy.web.management.enums.GenderEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/11 下午7:45
 */
public class User extends BaseEntity implements Serializable {
    private Long id;
    private Integer deptId;
    private String username;
    private String fullName;
    private String password;
    private String phone;
    private String email;
    private GenderEnum gender;
    private Boolean isDelete;

    private String lang;

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(deptId, user.deptId) && Objects.equals(username, user.username) && Objects.equals(fullName, user.fullName) && Objects.equals(password, user.password) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && gender == user.gender && Objects.equals(isDelete, user.isDelete) && Objects.equals(lang, user.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, deptId, username, fullName, password, phone, email, gender, isDelete, lang);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", isDelete=" + isDelete +
                ", lang='" + lang + '\'' +
                '}';
    }
}