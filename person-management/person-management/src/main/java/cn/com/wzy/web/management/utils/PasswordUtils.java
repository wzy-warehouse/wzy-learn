package cn.com.wzy.web.management.utils;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author wzy
 * @description: TODO(密码处理工具类)
 * @date 2025/7/12 下午5:18
 */
public class PasswordUtils {
    // 设置加密强度
    private static final int LOG_ROUNDS = 12;

    /**
     * 加密密码
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public static String encodePassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(LOG_ROUNDS));
    }

    /**
     * 验证密码是否匹配
     * @param rawPassword 原始密码（用户输入）
     * @param encodedPassword 加密后的密码（数据库存储）
     * @return 是否匹配
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}