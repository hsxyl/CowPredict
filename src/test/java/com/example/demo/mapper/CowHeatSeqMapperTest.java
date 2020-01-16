package com.example.demo.mapper;

import com.example.demo.DemoApplicationTests;
import com.example.demo.model.CowHeatSeqPO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.util.Assert;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/30
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CowHeatSeqMapperTest extends DemoApplicationTests {
    @Autowired
    CowHeatSeqMapper cowHeatSeqMapper;

    @Test
    public void test1Insert() {
        try {
            CowHeatSeqPO seqPO =  new CowHeatSeqPO();
            seqPO.setId(1L).setHeats("12.1,22.9").setStartTime("2019-11-11").setTimeUnit("DAY");
            cowHeatSeqMapper.insert(seqPO);
        } catch (UncategorizedSQLException ex) {
            System.out.println("主键已存在");
        }
    }

    @Test
    public void test2Query() {
        CowHeatSeqPO seqPO = new CowHeatSeqPO();
        seqPO.setId(1L);
        CowHeatSeqPO cows = cowHeatSeqMapper.selectOneById(seqPO);
        System.out.println(cows);
    }

    @Test
    public void test3Update() {
        CowHeatSeqPO seqPO = new CowHeatSeqPO().setId(1L);
        seqPO = cowHeatSeqMapper.selectOneById(seqPO);
        seqPO.setHeats("11.2,323,222");
        int sum = cowHeatSeqMapper.update(seqPO);
        Assert.isTrue(sum==1,"更新结果不为1");
    }

    @Test
    public void test4delete() {
        CowHeatSeqPO seqPO = new CowHeatSeqPO().setId(1L);
        seqPO = cowHeatSeqMapper.selectOneById(seqPO);
        long sum = cowHeatSeqMapper.delete(seqPO);
        Assert.isTrue(sum== 1L,"删除结果不是1");
    }

}
