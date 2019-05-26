package com.system.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

@Component
public class UserFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /**
         * pre：可以在请求被路由之前调用
         * routing：在路由请求时候被调用
         * post：在route和error过滤器之后被调用
         * error：处理请求时发生错误时被调用
         */
        // 过滤器的类型
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 优先级为0，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器，此处为true，说明需要过滤
        return true;
    }

    @Override
    public Object run() {
        System.out.println("access token ok");
        return null;
    }
}
