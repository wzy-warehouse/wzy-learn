package cn.com.wzy.web.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author wzy
 * @description: TODO(允许跨域访问)
 * @date 2025/7/13 下午9:27
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许来自 localhost:8888 的跨域请求
        config.addAllowedOriginPattern("http://localhost:8888");
        config.setAllowCredentials(true); // 允许携带凭证（如 Cookie）
        config.addAllowedMethod("*");     // 允许所有 HTTP 方法
        config.addAllowedHeader("*");     // 允许所有请求头

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对所有接口都有效

        return new CorsFilter(source);
    }
}