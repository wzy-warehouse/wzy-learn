package cn.com.wzy.web.management.config;

import cn.com.wzy.web.management.interceptor.WebInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wzy
 * @description: TODO(注册前端请求拦截器)
 * @date 2025/7/12 下午6:55
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private WebInterceptor webInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor)
                .addPathPatterns("/**")          // 拦截所有路径
                .excludePathPatterns("/login");    // 排除登录接口
    }
}