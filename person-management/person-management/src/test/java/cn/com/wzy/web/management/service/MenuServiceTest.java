package cn.com.wzy.web.management.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/15 下午1:32
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuServiceTest {
    @Autowired
    private IMenuService menuService;

    @Test
    public void queryAllMenusTest() {
        System.err.println(menuService.queryAllMenus());
    }
}