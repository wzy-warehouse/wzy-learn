package cn.com.wzy.web.management.service.ex;

/**
 * @author wzy
 * @description: TODO(密码错误异常)
 * @date 2025/7/13 上午8:32
 */
public class PasswordErrorException extends ServiceException{
    public PasswordErrorException() {
    }

    public PasswordErrorException(String message) {
        super(message);
    }

    public PasswordErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordErrorException(Throwable cause) {
        super(cause);
    }

    public PasswordErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}