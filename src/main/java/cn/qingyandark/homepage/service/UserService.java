package cn.qingyandark.homepage.service;

import cn.qingyandark.homepage.domain.User;

import java.util.List;

public interface UserService {
    public boolean save(User user);

    public boolean update(User user);

    public boolean deleteById(User user);

    public boolean getByNamePassword(User user);

    public User getById(User user);

    public List<User> selectALL();

    public List<User> selectByIds(List<Integer> ids);
}
