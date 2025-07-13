package cn.com.wzy.web.management.controller;

import cn.com.wzy.web.management.domain.ApiResponse;
import cn.com.wzy.web.management.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wzy
 * @description: TODO(控制层基类 ， 用于处理控制层相同代码)
 * @date 2025/7/12 下午6:28
 */
public class BaseController {
    // 用于统一处理抛出的异常
    @ExceptionHandler(ServiceException.class)
    public ApiResponse<Void> handleException(Throwable e) {
        if (e instanceof UserRegisteredException || e instanceof UserRegistrationFailedException) {
            return ApiResponse.error(409, e.getMessage());
        } else if (e instanceof UnknownException) {
            return ApiResponse.error(503, e.getMessage());
        } else if (e instanceof UserDoesNotExistException) {
            return ApiResponse.error(404, e.getMessage());
        } else if (e instanceof  PasswordErrorException) {
            return ApiResponse.error(401, e.getMessage());
        } else {
            return null;
        }
    }
}