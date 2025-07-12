package cn.com.wzy.web.management.service.impl;

import cn.com.wzy.web.management.domain.ApiResponse;
import cn.com.wzy.web.management.entity.User;
import cn.com.wzy.web.management.mapper.UserMapper;
import cn.com.wzy.web.management.service.IUserService;
import cn.com.wzy.web.management.service.ex.UnknownException;
import cn.com.wzy.web.management.service.ex.UserRegisteredException;
import cn.com.wzy.web.management.service.ex.UserRegistrationFailedException;
import cn.com.wzy.web.management.utils.I18nCacheUtils;
import cn.com.wzy.web.management.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        // 判断用户是否被注册过
        User registrationStatus = userMapper.findByUsername(user.getUsername());

        if(registrationStatus != null){
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
            if(!flag){
                throw new UserRegistrationFailedException(I18nCacheUtils.getMessage("user.register.fail.text"));
            }
        }catch (Exception e) {
            // 手动回滚插入操作
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new UnknownException(e.getCause());
        }
    }
}