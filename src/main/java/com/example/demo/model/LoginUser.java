package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/4
 */
@Data
@Accessors(chain = true)
public class LoginUser {
    Integer id;
    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
    String avatar;
    String name;

}
