package com.example.demo.model;

import com.example.demo.constant.UserPrivilegeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/10/30
 */
@Data
@Accessors(chain = true)
public class User {
    private Long id;

    private String userName;

    private String passWord;

    /**
     * 用户身份
     */
    private String privilege;

    /**
     * 注册时间
     */
    private String registerTime;

    private String email;

    public User(String userName,String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User setPrivilege(String privilege) {
        Assert.isTrue(privilege.equals(UserPrivilegeEnum.ADMIN.name())
                ||privilege.equals(UserPrivilegeEnum.VISITOR.name()),
                "身份信息必须是VISITOR或ADMIN");
        this.privilege = privilege;
        return this;
    }
}
