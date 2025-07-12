package cn.com.wzy.web.management.service.ex;

/**
 * @author wzy
 * @description: TODO(用户注册失败异常)
 * @date 2025/7/12 下午6:18
 */
public class UserRegistrationFailedException extends ServiceException{
    public UserRegistrationFailedException() {
    }

    public UserRegistrationFailedException(String message) {
        super(message);
    }

    public UserRegistrationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegistrationFailedException(Throwable cause) {
        super(cause);
    }

    public UserRegistrationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}