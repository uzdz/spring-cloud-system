package com.uzdz.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * token令牌过滤器
 * @author uzdz
 * @date: 2019/5/26 22:03
 * @since 0.1.0
 */
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        // 过滤器的类型
        // pre：可以在请求被路由之前调用
        // routing：在路由请求时候被调用
        // post：在route和error过滤器之后被调用
        // error：处理请求时发生错误时被调用
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 优先级为0，数字越大，优先级越低
        return PRE_DECORATION_FILTER_ORDER -1 ;
    }

    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器，此处为true，说明需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        // 从URL参数里获取值，可以通过cookie或header获取
        String token = request.getParameter("token");

        if (StringUtils.isEmpty(token)) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
