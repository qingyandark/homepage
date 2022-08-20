package cn.qingyandark.homepage.shiro;


import cn.qingyandark.homepage.domain.User;
import cn.qingyandark.homepage.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");

        // 这块可以从数据库查询，也可以根据需要自行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();// 认证查询的时候，需要把信息放进去，这里才能查到，详见最后一行

        // 设置权限
        info.addStringPermission(user.getPerms());
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        User user = userService.getUserByUsername(token.getUsername());

        if(user == null){
            return null; // 抛出用户不存在异常
        }

        /*---------shiro-thymeleaf---------*/
        Subject current = SecurityUtils.getSubject();
        Session session = current.getSession();
        session.setAttribute("loginuser", user);
        /*---------------------*/

        // 密码认证，由shiro完成
        // 可以加密 md5 md5盐值加密
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
