package cn.com.wzy.web.management.service.impl;

import cn.com.wzy.web.management.entity.User;
import cn.com.wzy.web.management.mapper.UserMapper;
import cn.com.wzy.web.management.service.IUserService;
import cn.com.wzy.web.management.service.ex.*;
import cn.com.wzy.web.management.utils.I18nCacheUtils;
import cn.com.wzy.web.management.utils.PasswordUtils;
import cn.com.wzy.web.management.utils.UserRedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/12 下午12:46
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRedisUtils userRedisUtils;

    @Value("${sa-token.timeout}")
    private Integer cacheTime;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        // 判断用户是否被注册过
        User registrationStatus = userMapper.findByUsername(user.getUsername());

        if (registrationStatus != null) {
            throw new UserRegisteredException(I18nCacheUtils.getMessage("user.occupy.text"));
        }

        // 进行注册
        try {
            // 密码加密
            user.setPassword(PasswordUtils.encodePassword(user.getPassword()));
            // 添加其余数据
            user.setDelete(false);
            user.setCreateUser(user.getUsername());
            user.setModifiedUser(user.getUsername());
            Date now = new Date();
            user.setCreateTime(now);
            user.setModifiedTime(now);

            boolean flag = userMapper.insertUser(user);
            if (!flag) {
                throw new UserRegistrationFailedException(I18nCacheUtils.getMessage("user.register.fail.text"));
            }
        } catch (Exception e) {
            // 手动回滚插入操作
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new UnknownException(e.getCause());
        }
    }

    @Override
    public User login(User user) {
        // 根据username获取用户信息
        User tempUser = userMapper.findByUsername(user.getUsername());
        if (tempUser == null || tempUser.getUsername() == null || tempUser.getDelete()) {
            throw new UserDoesNotExistException(I18nCacheUtils.getMessage("user.does.not.exist.text"));
        }
        // 验证密码
        boolean res = PasswordUtils.matchesPassword(user.getPassword(), tempUser.getPassword());
        if (!res) {
            throw new PasswordErrorException(I18nCacheUtils.getMessage("user.password.error.text"));
        }
        // 缓存到redis
        userRedisUtils.saveUser(tempUser, cacheTime);

        // 设置返回值
        User userInfo = new User();
        userInfo.setid(tempUser.getid());
        userInfo.setUsername(tempUser.getUsername());
        userInfo.setFullName(tempUser.getFullName());

        return userInfo;
    }

    @Override
    public User getLoggedInUserInformation(Long userId) {
        return userRedisUtils.getUser(userId);
    }
}