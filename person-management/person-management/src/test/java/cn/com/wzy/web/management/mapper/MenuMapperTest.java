package cn.com.wzy.web.management.mapper;

import cn.com.wzy.web.management.utils.I18nCacheUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/15 上午11:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuMapperTest {
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void queryAllMenusTest() {
        System.err.println(menuMapper.queryAllMenus());
    }
}