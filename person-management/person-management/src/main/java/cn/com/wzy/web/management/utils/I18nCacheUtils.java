package cn.com.wzy.web.management.utils;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 国际化语言文件缓存工具类 - 优化版
 * 使用Caffeine LoadingCache实现自动刷新
 */
public class I18nCacheUtils {

    // 使用LoadingCache支持refreshAfterWrite
    private static final LoadingCache<String, Properties> LANGUAGE_CACHE = Caffeine.newBuilder()
            .maximumSize(100)                  // 最多缓存100个语言文件
            .expireAfterAccess(1, TimeUnit.HOURS)  // 访问后1小时过期
            .refreshAfterWrite(30, TimeUnit.MINUTES) // 写入后30分钟刷新
            .build(new CacheLoader<String, Properties>() {
                @Override
                public Properties load(String fileName) {
                    return loadFromFile(fileName);
                }

                @Override
                public Properties reload(String fileName, Properties oldValue) {
                    return loadFromFile(fileName);
                }
            });

    // 语言文件基础路径（根据实际项目调整）
    private static final String BASE_PATH = "i18n/";

    // 全局语言上下文，使用ThreadLocal确保线程安全
    private static final ThreadLocal<Locale> LOCALE_CONTEXT = ThreadLocal.withInitial(() -> Locale.CHINA);

    /**
     * 设置当前线程的语言环境
     * @param lang 语言标签，如"zh-CN", "en-US"
     */
    public static void setLanguage(String lang) {
        if (lang == null || lang.trim().isEmpty()) {
            LOCALE_CONTEXT.set(Locale.CHINA); // 默认中文
            return;
        }

        // 解析语言标签，如"zh-CN"转换为Locale对象
        String[] parts = lang.split("-");
        if (parts.length == 1) {
            LOCALE_CONTEXT.set(new Locale(parts[0]));
        } else if (parts.length >= 2) {
            LOCALE_CONTEXT.set(new Locale(parts[0], parts[1]));
        } else {
            LOCALE_CONTEXT.set(Locale.CHINA); // 解析失败时使用默认值
        }

        // 刷新语言缓存
        refreshCache(LOCALE_CONTEXT.get());
    }

    /**
     * 清除当前线程的语言环境（恢复默认）
     */
    public static void clearLanguage() {
        LOCALE_CONTEXT.remove();
    }

    /**
     * 获取当前线程的语言环境
     */
    public static Locale getCurrentLocale() {
        return LOCALE_CONTEXT.get();
    }

    /**
     * 获取当前语言的本地化消息
     * @param key 消息键
     * @return 本地化消息，如果未找到则返回key
     */
    public static String getMessage(String key) {
        return getMessage(LOCALE_CONTEXT.get(), key);
    }

    /**
     * 获取带参数的消息
     * @param key 消息键
     * @param args 消息参数
     * @return 格式化后的消息
     */
    public static String getMessage(String key, Object... args) {
        String pattern = getMessage(LOCALE_CONTEXT.get(), key);
        return formatMessage(pattern, args);
    }

    /**
     * 根据指定语言获取本地化消息
     * @param key 消息键
     * @param lang 语言
     * @return 本地化消息，如果未找到则返回key
     */
    public static String getMessage(String key, String lang) {
        setLanguage(lang);
        return getMessage(LOCALE_CONTEXT.get(), key);
    }

    /**
     * 获取指定语言的本地化消息
     * @param locale 语言区域
     * @param key 消息键
     * @return 本地化消息，如果未找到则返回key
     */
    public static String getMessage(Locale locale, String key) {
        String fileName = getFileName(locale);
        Properties properties = LANGUAGE_CACHE.get(fileName);
        return properties.getProperty(key, key); // 未找到时返回key
    }

    /**
     * 获取当前语言的所有消息
     * @return 包含所有消息的Map
     */
    public static Map<String, String> getAllMessages() {
        return getAllMessages(LOCALE_CONTEXT.get());
    }

    /**
     * 获取指定语言的所有消息
     * @param locale 语言区域
     * @return 包含所有消息的Map
     */
    public static Map<String, String> getAllMessages(Locale locale) {
        String fileName = getFileName(locale);
        Properties properties = LANGUAGE_CACHE.get(fileName);
        Map<String, String> result = new HashMap<>();
        properties.forEach((k, v) -> result.put(k.toString(), v.toString()));
        return result;
    }

    /**
     * 从文件系统加载语言文件
     * @param fileName 文件名
     * @return 语言文件内容的Properties对象
     */
    private static Properties loadFromFile(String fileName) {
        Properties properties = new Properties();
        // 构建完整的类路径资源路径
        String resourcePath = BASE_PATH + fileName;

        try {
            // 使用ClassPathResource加载资源
            ClassPathResource resource = new ClassPathResource(resourcePath);

            if (!resource.exists()) {
                // 尝试加载默认文件
                if (!"message.properties".equals(fileName)) {
                    return loadFromFile("message.properties");
                }
                System.err.println("Language file not found: " + resourcePath);
                return properties;
            }

            // 使用UTF-8读取文件
            try (InputStream inputStream = resource.getInputStream();
                 InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                properties.load(reader);
            }
        } catch (IOException e) {
            System.err.println("Failed to load language file: " + resourcePath);
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 构建语言文件名
     * @param locale 语言区域
     * @return 完整文件名
     */
    private static String getFileName(Locale locale) {
        if (locale == null ||
                (locale.getLanguage().isEmpty() && locale.getCountry().isEmpty())) {
            return "message.properties";
        }

        // 文件名格式: message_{language}-{country}.properties
        String language = locale.getLanguage().toLowerCase();
        String country = locale.getCountry().toUpperCase();

        if (language.isEmpty()) {
            return "message.properties";
        } else if (country.isEmpty()) {
            return "message_" + language + ".properties";
        } else {
            return "message_" + language + "-" + country + ".properties";
        }
    }

    /**
     * 手动刷新指定语言的缓存
     * @param locale 语言区域
     */
    public static void refreshCache(Locale locale) {
        String fileName = getFileName(locale);
        LANGUAGE_CACHE.refresh(fileName);
    }

    /**
     * 清空所有缓存
     */
    public static void clearAllCache() {
        LANGUAGE_CACHE.invalidateAll();
    }

    /**
     * 格式化消息（支持MessageFormat）
     * @param pattern 消息模式
     * @param args 参数
     * @return 格式化后的消息
     */
    private static String formatMessage(String pattern, Object... args) {
        if (args == null || args.length == 0) {
            return pattern;
        }
        try {
            return MessageFormat.format(pattern, args);
        } catch (IllegalArgumentException e) {
            System.err.println("Message formatting error: " + pattern);
            e.printStackTrace();
            return pattern;
        }
    }
}