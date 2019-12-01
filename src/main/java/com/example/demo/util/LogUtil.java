package com.example.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/12
 */
public class LogUtil {

    /**
     * 获取异常栈
     * @param ex
     * @return
     */
    public static String getStackTrace(Exception ex) {
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(byteArrayInputStream));
        return byteArrayInputStream.toString();
    }
}
