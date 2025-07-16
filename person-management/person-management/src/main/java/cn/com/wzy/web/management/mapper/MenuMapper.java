package cn.com.wzy.web.management.mapper;

import cn.com.wzy.web.management.entity.Menu;

import java.util.List;

/**
 * @author wzy
 * @description: TODO(处理菜单接口)
 * @date 2025/7/15 上午10:59
 */
public interface MenuMapper {
    /**
     * 获取所有菜单
     * @return 菜单集合
     */
    List<Menu> queryAllMenus();
}
