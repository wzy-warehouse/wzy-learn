package cn.com.wzy.web.management.utils;

import cn.com.wzy.web.management.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @description: TODO(用户缓存信息管理)
 * @date 2025/7/13 上午9:50
 */

@Component
public class UserRedisUtils {

    // Redis键前缀
    private static final String USER_KEY_PREFIX = "user:";

    // 最大有效期（1天）
    private static final long MAX_EXPIRE_SECONDS = 86400;

    private final RedisTemplate<String, Object> redisTemplate;

    public UserRedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 存储用户信息到Redis
     * @param userInfo 用户信息Map
     * @param expireSeconds 有效期，单位秒
     */
    public void saveUser(User userInfo, int expireSeconds) {
        if (userInfo.getid() == null || userInfo.getid() == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }

        // 计算实际有效期
        long actualExpire = Math.min(expireSeconds, MAX_EXPIRE_SECONDS);

        // 构建Redis键
        String key = buildUserKey(userInfo.getid().toString());

        // 存储数据
        redisTemplate.opsForValue().set(key, userInfo, actualExpire, TimeUnit.SECONDS);
    }

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户对象，如果不存在返回null
     */
    public User getUser(Long userId) {
        String key = buildUserKey(userId.toString());
        return (User)redisTemplate.opsForValue().get(key);
    }

    /**
     * 手动清除用户数据
     * @param userId 用户ID
     */
    public void clearUserData(Long userId) {
        String key = buildUserKey(userId.toString());
        redisTemplate.delete(key);
    }

    /**
     * 获取用户剩余有效期
     * @param userId 用户ID
     * @return 剩余天数，-1表示永不过期，-2表示键不存在
     */
    public long getRemainingExpireDays(Long userId) {
        String key = buildUserKey(userId.toString());
        Long expireSeconds = redisTemplate.getExpire(key, TimeUnit.SECONDS);

        if (expireSeconds == null) return -2;
        if (expireSeconds == -1) return -1;

        return TimeUnit.SECONDS.toDays(expireSeconds);
    }

    /**
     * 刷新用户有效期（重置为30天）
     * @param userId 用户ID
     */
    public void refreshExpiration(Long userId) {
        String key = buildUserKey(userId.toString());
        User user = getUser(userId);
        if (user != null) {
            saveUser(user, 30); // 重新保存以刷新有效期
        }
    }

    /**
     * 构建Redis键
     * @param userId 用户ID
     * @return 完整的Redis键
     */
    private String buildUserKey(String userId) {
        return USER_KEY_PREFIX + userId;
    }
}