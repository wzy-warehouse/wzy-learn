package cn.com.wzy.web.management.service.ex;

/**
 * @author wzy
 * @description: TODO(未知异常，可能是硬件宕机等异常)
 * @date 2025/7/12 下午6:19
 */
public class UnknownException extends ServiceException{
    public UnknownException() {
    }

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownException(Throwable cause) {
        super(cause);
    }

    public UnknownException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}