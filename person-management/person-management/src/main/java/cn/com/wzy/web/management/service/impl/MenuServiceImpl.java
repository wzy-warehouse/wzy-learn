package cn.com.wzy.web.management.service.impl;

import cn.com.wzy.web.management.entity.Menu;
import cn.com.wzy.web.management.mapper.MenuMapper;
import cn.com.wzy.web.management.service.IMenuService;
import cn.com.wzy.web.management.service.ex.UnknownException;
import cn.com.wzy.web.management.utils.I18nCacheUtils;
import cn.com.wzy.web.management.vo.MenuListVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/15 上午11:42
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuListVo> queryAllMenus() {
        // 获取菜单列表
        try {
            // 获取所有菜单数据
            List<Menu> menuList = menuMapper.queryAllMenus();
            String language = I18nCacheUtils.getCurrentLanguage(null);

            // 创建映射表：menuId -> MenuListVo
            Map<Long, MenuListVo> menuMap = new HashMap<>();
            // 存储根菜单（顶级菜单）
            List<MenuListVo> rootMenus = new ArrayList<>();

            // 第一遍遍历：创建所有菜单项并建立映射
            for (Menu menu : menuList) {
                // 转换菜单名称（根据当前语言）
                String title = convertMenuName(menu.getMenuName(), language);

                // 创建菜单项VO（children初始化为空列表）
                MenuListVo menuVo = new MenuListVo(
                        menu.getMenuId(),
                        menu.getRouteName(),
                        menu.getPath(),
                        menu.getComponent(),
                        title,
                        menu.getIcon(),
                        new ArrayList<>()  // 初始化children为空列表
                );

                // 添加到映射表
                menuMap.put(menu.getMenuId(), menuVo);
            }

            // 第二遍遍历：构建父子关系
            for (Menu menu : menuList) {
                MenuListVo currentMenu = menuMap.get(menu.getMenuId());
                Long parentId = menu.getParentId();

                if (parentId == null || parentId == 0) {
                    // 根菜单项，直接添加到结果列表
                    rootMenus.add(currentMenu);
                } else {
                    // 子菜单项，找到父菜单并添加到其children
                    MenuListVo parentMenu = menuMap.get(parentId);
                    if (parentMenu != null) {
                        parentMenu.getChildren().add(currentMenu);
                    } else {
                        // 处理异常情况：父菜单不存在
                        rootMenus.add(currentMenu); // 作为根菜单处理
                    }
                }
            }
            return rootMenus;
        } catch (UnknownException e) {
            throw new UnknownException(e.getCause());
        }
    }

    /**
     * 转换菜单名称
     * @param menuName 菜单名称
     * @param language 语言
     * @return 菜单
     */
    private String convertMenuName(String menuName, String language) {
        Map<String, String> menuMap = JSON.parseObject(menuName, Map.class);
        switch (language.toUpperCase()) {
            case "EN-US":
                return menuMap.get("en_US");
            default:
                return menuMap.get("zh_CN");
        }
    }
}