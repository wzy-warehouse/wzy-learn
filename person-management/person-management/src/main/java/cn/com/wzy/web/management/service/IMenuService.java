package cn.com.wzy.web.management.service;

import cn.com.wzy.web.management.vo.MenuListVo;

import java.util.List;

/**
 * @author wzy
 * @description: TODO(菜单处理接口)
 * @date 2025/7/15 上午11:40
 */
public interface IMenuService {
    /**
     * 获取所有菜单
     * @return 菜单列表
     */
    List<MenuListVo> queryAllMenus();
}
