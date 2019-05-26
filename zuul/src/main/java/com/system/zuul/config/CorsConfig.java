package com.system.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * zuul支持跨域
 * @author uzdz
 * @date: 2019/5/26 22:20
 * @since 0.1.0
 */
@Component
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        // 允许跨域的域名
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        // 设置跨域缓存时间
        config.setMaxAge(300L);

        // 设置过滤url地址
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
