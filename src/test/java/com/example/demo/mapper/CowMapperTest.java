package com.example.demo.mapper;

import com.example.demo.DemoApplicationTests;
import com.example.demo.Model.Cow;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author xushenbao
 * @desc 奶牛的映射
 * @create 2019/11/2
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CowMapperTest extends DemoApplicationTests {
    @Autowired
    CowMapper cowMapper;

    @Test
    public void test1Insert() {
        try {
            Cow cow = new Cow();
            cow.setName("test")
                .setOld(5)
                .setCatchTime("2019-11-11 11:11:11")
                .setWeight(12.1)
                .setBreedStatus("NORMAL")
                .setIsHeat(Boolean.TRUE);

            cowMapper.insert(cow);
        } catch (UncategorizedSQLException ex) {
            System.out.println("主键已存在");
        }
    }

    @Test
    public void test2Query() {
        Cow cow = new Cow();
        cow.setName("test");
        List<Cow> cows = cowMapper.selectByCowAndPage(cow,null);
        System.out.println(cows);
    }

    @Test
    public void test3Update() {
        Cow cow = new Cow().setName("test");
        Cow cowNew = cowMapper.selectOne(cow);
        cowNew.setName("tt");
        long sum = cowMapper.update(cowNew);
        Assert.isTrue(sum==1,"更新结果不为1");
    }

    @Test
    public void test4delete() {
        Cow cow = new Cow().setName("tt");
        Cow cowD = cowMapper.selectOne(cow);
        long sum = cowMapper.delete(cowD);
        Assert.isTrue(sum== 1L,"删除结果不是1");
    }

}
