package cn.com.wzy.web.management.controller;

import cn.com.wzy.web.management.domain.ApiResponse;
import cn.com.wzy.web.management.entity.User;
import cn.com.wzy.web.management.service.IUserService;
import cn.com.wzy.web.management.service.ex.UnknownException;
import cn.com.wzy.web.management.service.ex.UserRegisteredException;
import cn.com.wzy.web.management.service.ex.UserRegistrationFailedException;
import cn.com.wzy.web.management.utils.I18nCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/12 下午5:45
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    IUserService userService;

    @PostMapping("register")
    public ApiResponse<?> register(@RequestBody User user) {
        if(user.getLang() != null) I18nCacheUtils.setLanguage(user.getLang());
        userService.register(user);
        return ApiResponse.success();
    }

    @GetMapping("test")
    public ApiResponse<?> test(@RequestBody User user) {
        userService.register(user);
        return ApiResponse.success();
    }
}