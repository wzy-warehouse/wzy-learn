package cn.com.wzy.web.management.service.ex;

/**
 * @author wzy
 * @description: TODO(用户被注册异常)
 * @date 2025/7/12 下午6:17
 */
public class UserRegisteredException extends ServiceException{
    public UserRegisteredException() {
    }

    public UserRegisteredException(String message) {
        super(message);
    }

    public UserRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegisteredException(Throwable cause) {
        super(cause);
    }

    public UserRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}