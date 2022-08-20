package cn.qingyandark.homepage.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController2 {

    //    @ResponseBody
    @RequestMapping({"/", "/index"})
    public String toIndex(Model model){
        model.addAttribute("msg", "hello, shiro");
        return "index";
    }

    @RequestMapping({"/login"})
    public String toLogin(Model model){
        model.addAttribute("msg", "login");
        return "login";
    }

    @RequestMapping("/user/add")
    public String toAdd(Model model){
        model.addAttribute("msg", "add");
        return "user/add";
    }

    /**
     * 只有管理和超级管理员才可以获取用户列表，并获取数据
     * 只有拥有 admin:getUserList 权限的用户才可以查看
     * @param model
     * @return user/list.html & data
     */
    @RequiresRoles({"管理员", "超级管理员"})
    @RequestMapping("/user/list")
    @RequiresPermissions("admin:getUserList")
    public String toUserList(Model model){
        model.addAttribute("msg", "del");
        return "user/list";
    }

    @RequestMapping("/user/del")
    public String toDel(Model model){
        model.addAttribute("msg", "del");
        return "user/del";
    }

    @RequestMapping("/toerror")
    public String toError(Model model){
        model.addAttribute("msg", "toerror");
        return "error";
    }

    @RequestMapping("/loginCheck")
    public String login(String username, String password, Model model){
        // 1.获取当前对象
        Subject subject = SecurityUtils.getSubject();

        // 2.封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 3.验证登录，自动执行登陆方法
        try {
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg", "user not exist");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "password wrong");
            return "login";
        }
    }

    @RequestMapping("/unauth")
    @ResponseBody
    public String unauthorized(Model model){
        return "未经授权无法访问";
    }
}