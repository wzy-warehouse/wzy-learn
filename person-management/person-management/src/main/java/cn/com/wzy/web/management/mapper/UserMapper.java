package cn.com.wzy.web.management.mapper;

import cn.com.wzy.web.management.entity.User;

/**
 * 用户模块持久层接口
 */
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 是否成功插入
     */
    Boolean insertUser(User user);

    /**
     * 根据用户名来查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    User findByUsername(String username);
}
