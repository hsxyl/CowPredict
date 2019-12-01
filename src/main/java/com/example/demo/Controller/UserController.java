package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import com.example.demo.result.Failures;
import com.example.demo.result.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/1
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/query")
    public ResultVO queryUser(@Valid User user) {
        System.out.println(user);
        try {
            return ResultVO.success(userService.queryUser(user));
        } catch (Exception ex) {
            log.error("Fail to queryUser,param is [{}],exception:",user,ex);
            return Failures.SERVER_ERROR;
        }
    }

    @GetMapping("/insert")
    public ResultVO insertUser(@Valid User user) {
        try {
            if(!CollectionUtils.isEmpty(userService.queryUser(user))) {
                return Failures.USERNAME_EXISTS;
            }
            return ResultVO.success(userService.addUser(user));
        } catch (Exception ex) {
            log.error("Fail to insertUser,param is [{}],exception:",user,ex);
            return Failures.SERVER_ERROR;
        }
    }

    @GetMapping("/update")
    public ResultVO updateUser(@Valid User user) {
        try {
            Assert.notNull(user.getId(),"用户id不能为空");
            if(userService.queryUserById(user.getId())==null) {
                return Failures.USERNAME_UNEXISTS;
            }
            return ResultVO.success(userService.updateUser(user));
        } catch (IllegalArgumentException ex) {
          log.error("Illegal argument:[{}]",user,ex);
          ResultVO resultVO = new ResultVO();
          resultVO.setMsg("参数错误"+ex.getMessage());
          return resultVO;
        } catch(Exception ex) {
            log.error("Fail to updateUser,param is [{}],exception:",user,ex);
            return Failures.SERVER_ERROR;
        }
    }

    @GetMapping("/delete")
    public ResultVO deleteUser(@Valid User user) {
        try {
            if(userService.queryUser(user)==null) {
                return Failures.USERNAME_UNEXISTS;
            }
            return ResultVO.success(userService.deleteUser(user));
        } catch (Exception ex) {
            log.error("Fail to deleteUser,param is [{}],exception:",user,ex);
            return Failures.SERVER_ERROR;
        }
    }

}
