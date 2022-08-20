package cn.qingyandark.homepage.shiro;

//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    //创建ShiroFilterFactoryBean工厂对象 3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //配置SecurityManager
        bean.setSecurityManager(securityManager);

        // shiro 内置过滤器
        /*
            anon: 无需认证
            authc: 必须认证
            user: 必须拥有记住我功能
            perms：拥有对某个资源的权限
            role：拥有某个角色全新啊

         */
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        // 授权，正常情况下，没有授权会跳转到为授权页面
        map.put("/user/add", "perms[user:add]"); //拥有user:add权限
        map.put("/user/del", "perms[user:del]"); //拥有user:del权限

        // 开始拦截，某写资源访问必须认证

//        map.put("/user/add", "authc"); //add页面所有人可以访问
//        map.put("/user/del", "authc"); //add页面所有人可以访问
        map.put("/user/*", "authc");

//
//        //所有功能放心  前后端分离认证拦截是基于session  每次请求的session不同
//        // 设置认证界面路径

        bean.setLoginUrl("/login"); //如果没有登录，跳转登陆页面
        bean.setUnauthorizedUrl("/unauth"); // 如果没有授权


        bean.setFilterChainDefinitionMap(map);

        return bean;
    }

    // 创建安全管理器 2
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建自定义Realm 1
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }


    // 以下为thymeleaf整合shiro

//    @Bean
//    public ShiroDialect getShiroDialect(){
//        return new ShiroDialect();
//    }
}
