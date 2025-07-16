package cn.com.wzy.web.management.controller;

import cn.com.wzy.web.management.domain.ApiResponse;
import cn.com.wzy.web.management.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/15 下午1:40
 */
@RestController
@RequestMapping("menus")
public class MenuController extends BaseController{
    @Autowired
    private IMenuService menuService;

    @PostMapping("lists")
    public ApiResponse<?> queryAllMenu(){
        return ApiResponse.success(menuService.queryAllMenus());
    }
}