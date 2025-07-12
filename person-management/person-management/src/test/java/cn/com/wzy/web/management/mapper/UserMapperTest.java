package cn.com.wzy.web.management.mapper;

import cn.com.wzy.web.management.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/12 上午11:38
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setDelete(false);
        boolean flag = userMapper.insertUser(user);
        System.out.println(flag);
    }

    @Test
    public void findByUsername() {
        System.out.println(userMapper.findByUsername("admin"));
    }
}