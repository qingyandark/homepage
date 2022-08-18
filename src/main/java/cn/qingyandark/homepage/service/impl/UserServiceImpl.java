package cn.qingyandark.homepage.service.impl;

import cn.qingyandark.homepage.domain.User;
import cn.qingyandark.homepage.mapper.UserMapper;
import cn.qingyandark.homepage.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    public boolean save(User user) {
        return mapper.insert(user) > 0;
    }

    public boolean update(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getId, user.getId());
        return mapper.update(user, lqw) > 0;
    }

    public boolean deleteById(User user) {
        return mapper.deleteById(user.getId()) > 0;
    }

    public boolean getByNamePassword(User user){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        return mapper.selectCount(lqw)>0;
    }

    public User getById(User user) {
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
//        lqw.eq(User::getId, user.getId());
//        return mapper.selectById(user, lqw);
        return mapper.selectById(user.getId());
    }

    public List<User> selectALL() {
        return mapper.selectList(null);
    }

    public List<User> selectByIds(List<Integer> ids) {
        return mapper.selectBatchIds(ids);
    }
}
