package com.example.demo.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/1
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User queryUserById(Long id) {
        return userMapper.selectById(id);
    }

    public List<User> queryUser(User user) {
        return userMapper.selectByUserAndPage(user,null);
    }

    public User addUser(User user) {
        userMapper.insert(user);
        return userMapper.selectOne(user);
    }

    public long updateUser(User user) {
        return userMapper.update(user);
    }

    public long deleteUser(User user) {
        return userMapper.delete(user);
    }


}
