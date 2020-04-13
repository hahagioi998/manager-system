package com.swpu.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {

    /**
     * 在请求前pre或者后post执行
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器的执行顺序，越小越先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启，true表示开启
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作 return任何object的值都表示放行
     * setSendZulResponse（false）表示不再继续执行
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
//        得到上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
//        得到request域
        HttpServletRequest request = currentContext.getRequest();
        if (request.getMethod().equals("OPTIONS")){
            return null;
        }
        if (request.getRequestURI().indexOf("login")>0){
            return null;
        }
        //放开图片显示请求
        if (request.getRequestURI().indexOf("showPicture")>0){
            return null;
        }
//        得到头信息
        String header = request.getHeader("Authorization");
//        if (header == null || "".equals(header)){
//            currentContext.setSendZuulResponse(false);
//            currentContext.setResponseStatusCode(401);
//            currentContext.setResponseBody("用户没有登录，请登录");
//            currentContext.getResponse().setContentType("text/html;charset=utf-8");
//        }
//        判断是否有头信息
        if (header != null && !"".equals(header)) {
            currentContext.addZuulRequestHeader("Authorization", header);
            return null;
        }

        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");

        return null;
    }
}
