package com.example.demo.mapper;

import com.example.demo.DemoApplicationTests;
import com.example.demo.model.PageContent;
import com.example.demo.model.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/10/30
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest extends DemoApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test1Insert() {
        try {
            User user = new User("fuck","fuck")
                    .setEmail("email")
                    .setPrivilege("ADMIN")
                    .setRegisterTime("2019-11-11");
            System.out.println(userMapper.insert(user));
        } catch (UncategorizedSQLException ex) {
            System.out.println("主键已存在");
        }
    }

    @Test
    public void test2Query() {
        User user = new User("fuck","fuck");
        PageContent page = new PageContent(1,5);
        List<User> users = userMapper.selectByUserAndPage(user,page);
        System.out.println(users);
    }

    @Test
    public void test3Update() {
        User user = new User("fuck","fuck");
        User selectUser = userMapper.selectOne(user);
        selectUser.setPassWord("fk");
        long sum = userMapper.update(selectUser);
        Assert.isTrue(sum== 1L,"更新结果不是1");
    }

    @Test
    public void test4Delete() {
        User user = new User("fuck","fk");
        User selectUser = userMapper.selectOne(user);
        long sum = userMapper.delete(selectUser);
        Assert.isTrue(sum== 1L,"删除结果不是1");
    }
}
