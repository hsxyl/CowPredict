package com.example.demo.result;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/1
 */
public class Failures {

    /**
     * 301，用户名已经存在
     */
    public static ResultVO USERNAME_EXISTS;

    /**
     * 302, 用户名不存在
     */
    public static ResultVO USERNAME_UNEXISTS;

    public static ResultVO COWNAME_EXISTS;

    public static ResultVO COWNAME_UNEXISTS;

    /**
     * 500, 服务端错误
     */
    public static ResultVO SERVER_ERROR;



    static {
        SERVER_ERROR = ResultVO.fail(500,"服务端错误");

        USERNAME_EXISTS = ResultVO.fail(301,"用户名已经存在");
        USERNAME_UNEXISTS = ResultVO.fail(302,"用户名不存在");
        COWNAME_EXISTS = ResultVO.fail(311,"奶牛名字已经存在");
        COWNAME_UNEXISTS = ResultVO.fail(312,"奶牛不存在");
    }
}
