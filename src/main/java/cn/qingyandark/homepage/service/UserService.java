package cn.qingyandark.homepage.service;

import cn.qingyandark.homepage.domain.User;

import java.util.List;

public interface UserService {
    public boolean save(User user);

    public boolean update(User user);

    public boolean deleteById(Integer id);

    public boolean getByNamePassword(User user);

    public User getById(Integer id);

    public List<User> selectALL();

    public List<User> selectByIds(List<Integer> ids);

    public User getUserByUsername(String username);
}
