package com.example.demo.mapper;

import com.example.demo.model.Device;
import com.example.demo.model.PageContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/30
 */
@Mapper
@Component
public interface DeviceMapper {
    Device selectById(Long id);

    List<Device> selectByDeviceAndPage(@Param("device") Device cow, @Param("page") PageContent page);

    Device selectOne(Device cow);

    int insert(Device cow);

    long update(Device cow);

    long delete(Device cow);
}
