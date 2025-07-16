package cn.com.wzy.web.management.domain;

import cn.com.wzy.web.management.utils.I18nCacheUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * API统一响应格式
 * @param <T> 响应数据的类型
 */
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionid = 1L;

    // 状态码
    private int code;
    // 消息
    private String message;
    // 时间戳
    private long timestamp;
    // 响应数据
    private T data;
    // 附加信息（可选）
    private Map<String, Object> extra;

    // 构造方法
    public ApiResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(int code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    // 成功响应的静态工厂方法
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, I18nCacheUtils.getMessage("return.success.text"));
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, I18nCacheUtils.getMessage("return.success.text"), data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    // 失败响应的静态工厂方法
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message);
    }

    public static <T> ApiResponse<T> error(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    // 常用错误类型的快捷方法
    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(400, message);
    }

    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<>(401, message);
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<>(403, message);
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(404, message);
    }

    public static <T> ApiResponse<T> internalError(String message) {
        return new ApiResponse<>(500, message);
    }

    // 链式调用方法
    public ApiResponse<T> addExtra(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap<>();
        }
        this.extra.put(key, value);
        return this;
    }

    // Getters and setters
    public int getCode() {
        return code;
    }

    public ApiResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ApiResponse<T> setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public ApiResponse<T> setExtra(Map<String, Object> extra) {
        this.extra = extra;
        return this;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                ", extra=" + extra +
                '}';
    }
}