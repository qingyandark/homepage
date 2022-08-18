package cn.qingyandark.homepage.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    /**
     * 实例化ShiroFilterFactoryBean  拦截到所有的请求，根据请求的不同做不同的处理
     *                         配置处理请求的各种方式，配置SecurityManager 等等
     * @return
     */
    //ShiroFilter过滤所有请求
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        //实例化ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //依赖注入
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 配置系统受限资源
        // 配置系统公共资源
        Map<String, String> map = new HashMap<String, String>();
        map.put("/index.jsp","authc");//表示这个资源需要认证和授权

        //所有功能放心  前后端分离认证拦截是基于session  每次请求的session不同
        // 设置认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    // 创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    //创建自定义Realm
    @Bean
    public Realm getRealm() {
        UserRealm realm = new UserRealm();
        return realm;
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login.html", "authc"); // need to accept POSTs from the login form
        chainDefinition.addPathDefinition("/logout", "logout");
        return chainDefinition;
    }

//    @Bean
//    public DefaultWebSecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
//        ArrayList<Realm> realms = new ArrayList<>();
//        realms.add(userRealm());   //UserRealm
//        realms.add(memberRealm());   //MemberRealmx1
//        //依赖注入Realm
//        securityManager.setRealms(realms);
//        return securityManager;
//    }
}
