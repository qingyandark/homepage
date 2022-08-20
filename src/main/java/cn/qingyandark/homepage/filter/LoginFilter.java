package cn.qingyandark.homepage.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@Component
@WebFilter(filterName = "userFilter",
        urlPatterns = "/*"
)
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("userFilter init ......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        System.out.println("url = " + request.getRequestURI());

        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);

        // 请求url中包含/hello||/online,继续执行
        if (request.getRequestURI().contains("/index")
                || request.getRequestURI().contains("/") ){
            // 交给下一个过滤器或servlet处理
            System.out.println("into LoginFilter ......");
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            // 请求url不包含/hello||/online,重定向到/online接口
            System.out.println("not into LoginFilter ......");
            wrapper.sendRedirect("/error");
        }
    }

    @Override
    public void destroy() {
        System.out.println("userFilter destory ......");
    }
}
