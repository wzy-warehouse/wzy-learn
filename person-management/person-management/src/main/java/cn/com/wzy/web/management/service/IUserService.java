package cn.com.wzy.web.management.service;

import cn.com.wzy.web.management.entity.User;

/**
 * @author wzy
 * @description: TODO(用户模块业务层接口)
 * @date 2025/7/12 下午12:45
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户数据对象
     */
    void register(User user);
}
