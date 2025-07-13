package cn.com.wzy.web.management.service;

import cn.com.wzy.web.management.domain.ApiResponse;
import cn.com.wzy.web.management.entity.User;
import cn.com.wzy.web.management.utils.I18nCacheUtils;
import cn.com.wzy.web.management.utils.PasswordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/12 下午3:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void verifyPassword() {
        System.err.println("密码验证情况：" + PasswordUtils.matchesPassword("133456",
                "$2a$12$0uTC0nEeNZ3tMVWJ0XfgV.cqdSE08AptT0Y8OKrYW52At/ciMi70O"));
    }
}