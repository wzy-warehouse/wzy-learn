package cn.com.wzy.web.management.service.ex;

/**
 * @author wzy
 * @description: TODO(用户不存在异常)
 * @date 2025/7/13 上午8:33
 */
public class UserDoesNotExistException extends ServiceException{
    public UserDoesNotExistException() {
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }

    public UserDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public UserDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}