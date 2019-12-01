package com.example.demo.mapper;

import com.example.demo.DemoApplicationTests;
import com.example.demo.Model.HelloModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/10/30
 */
public class HelloMapperTest extends DemoApplicationTests {
    @Autowired
    HelloMapper helloMapper;

    @Test
    public void insert() {
        HelloModel helloModel = new HelloModel().setText("fuck2").setTitle("fuck2");
        System.out.println(helloMapper.insert(helloModel));
        System.out.println(helloModel);

    }
}
