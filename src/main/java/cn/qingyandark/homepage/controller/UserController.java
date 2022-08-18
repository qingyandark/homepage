package cn.qingyandark.homepage.controller;

import cn.qingyandark.homepage.domain.Code;
import cn.qingyandark.homepage.domain.User;
import cn.qingyandark.homepage.domain.UserResult;
import cn.qingyandark.homepage.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

// rest风格
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserResult save(@RequestBody User user){
        boolean flag = userService.save(user);
        Integer code = flag ? Code.SAVE_OK:Code.SAVE_ERR;
        String msg = flag ? "添加成功" : "添加错误";
        return new UserResult(code, null, msg);
    }

    @DeleteMapping("/{id}")
    public UserResult delete(@PathVariable Long id){
        System.out.println(id);
        boolean flag = userService.deleteById(new User(id));
        Integer code = flag ? Code.DELETE_OK:Code.DELETE_ERR;
        String msg = flag ? "删除成功" : "删除错误";
        return new UserResult(code, null, msg);
    }

    @PutMapping
    public UserResult update(@RequestBody User user){
        System.out.println(user);
        boolean flag = userService.update(user);
        Integer code = flag ? Code.UPDATE_OK:Code.UPDATE_ERR;
        String msg = flag ? "更新成功" : "更新错误";
        return new UserResult(code, null, msg);
    }

    @GetMapping("/{id}")
    @RequiresRoles("admin")
    public UserResult getById(@PathVariable Long id){
        User user = userService.getById(new User(id));
        Integer code = user != null? Code.GET_OK : Code.GET_ERR;
        String msg = user != null? "查询成功" : "查询错误";
        return new UserResult(code, user, msg);
    }

    @GetMapping
    public UserResult getAll(){
        List<User> users = userService.selectALL();
        Integer code = users != null? Code.GET_OK : Code.GET_ERR;
        String msg = users != null? "查询成功" : "查询错误";
        return new UserResult(code, users, msg);
    }

}