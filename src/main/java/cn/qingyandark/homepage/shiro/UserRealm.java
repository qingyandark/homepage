package cn.qingyandark.homepage.shiro;


import cn.qingyandark.homepage.domain.User;
import cn.qingyandark.homepage.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Bean
    public Realm realm() {
        TextConfigurationRealm realm = new TextConfigurationRealm();
        realm.setUserDefinitions("user=password,user\n" +
                "admin=password,admin");

        realm.setRoleDefinitions("admin=read,write\n" +
                "user=read");
        realm.setCachingEnabled(true);
        return realm;
    }

    /**
     * 授权
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * 对用户身份进行认证，认证不通过则抛出 “AccountException” 异常
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        System.out.println("username = " + username);
        User realUser = userService.getById(new User());
        if(realUser == null){
            throw new AccountException();
        }
        return new SimpleAuthenticationInfo(realUser,realUser.getPassword(),getName());
    }
}
