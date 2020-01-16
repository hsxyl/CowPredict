package com.example.demo.mapper;

import com.example.demo.DemoApplicationTests;
import com.example.demo.model.Device;
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
 * @create 2019/11/30
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceMapperTest extends DemoApplicationTests {
    @Autowired
    DeviceMapper deviceMapper;

    @Test
    public void test1Insert() {
        try {
            Device device = new Device();
            device.setCowId(1L)
                    .setBuyTime("2019-11-11 11:11:11")
                    .setInstallTime("2019-11-11 11:11:11");

            deviceMapper.insert(device);
        } catch (UncategorizedSQLException ex) {
            System.out.println("主键已存在");
        }
    }

    @Test
    public void test2Query() {
        Device device = new Device();
        device.setBuyTime("2019-11-11 11:11:11");
        List<Device> devices = deviceMapper.selectByDeviceAndPage(device,null);
        Assert.isTrue(devices.size()==1,"查询结果不对");
    }

    @Test
    public void test3Update() {
        Device device = new Device().setCowId(1L);
        Device deviceNew = deviceMapper.selectOne(device);
        deviceNew.setBuyTime("2019-12-12 12:12:12");
        long sum = deviceMapper.update(deviceNew);
        Assert.isTrue(sum==1,"更新结果不为1");
    }

    @Test
    public void test4delete() {
        Device device = new Device().setBuyTime("2019-12-12 12:12:12");
        Device deviceD = deviceMapper.selectOne(device);
        long sum = deviceMapper.delete(deviceD);
        Assert.isTrue(sum== 1L,"删除结果不是1");
    }
}
