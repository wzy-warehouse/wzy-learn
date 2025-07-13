package cn.com.wzy.web.management.controller;

import cn.com.wzy.web.management.domain.ApiResponse;
import cn.com.wzy.web.management.entity.User;
import cn.com.wzy.web.management.service.IUserService;
import cn.com.wzy.web.management.utils.I18nCacheUtils;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wzy
 * @description: TODO()
 * @date 2025/7/12 下午5:45
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    IUserService userService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("register")
    public ApiResponse<?> register(@RequestBody User user) {
        if (user.getLang() != null) I18nCacheUtils.setLanguage(user.getLang());
        userService.register(user);
        return ApiResponse.success();
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("login")
    public ApiResponse<?> login(@RequestBody User user) {
        if (user.getLang() != null) I18nCacheUtils.setLanguage(user.getLang());
        User res = userService.login(user);

        // sa-token记录登录
        StpUtil.login(res.getUid());
        return ApiResponse.success(res);
    }

    /**
     * 验证是否登录
     * @return
     */
    @PostMapping("login_status")
    public ApiResponse<?> isLogin() {
        // 判断是否已经登录
        return StpUtil.isLogin() ? ApiResponse.success() : ApiResponse.error(401, I18nCacheUtils.getMessage("user.does.not.login.text"));
    }

    @GetMapping("test")
    public ApiResponse<?> test(Long userId) {
        System.err.println(StpUtil.isLogin(userId));
        System.err.println(StpUtil.getTokenValue());
        System.err.println(userService.getLoggedInUserInformation(userId));
        return ApiResponse.success(StpUtil.getTokenValue());
    }
}