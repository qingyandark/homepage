package cn.qingyandark.homepage.config;

import cn.qingyandark.homepage.interceptor.UserInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorAdapterConfig implements WebMvcConfigurer {
    private final UserInterceptor UserInterceptor;

    public InterceptorAdapterConfig(UserInterceptor UserInterceptor) {
        this.UserInterceptor = UserInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //注册自己的拦截器并设置拦截的请求路径
        registry.addInterceptor(UserInterceptor).addPathPatterns("/**");
    }
}
