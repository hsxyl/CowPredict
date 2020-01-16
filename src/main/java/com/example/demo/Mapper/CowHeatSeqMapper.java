package com.example.demo.mapper;

import com.example.demo.model.CowHeatSeqPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Mapper
@Component
public interface CowHeatSeqMapper {

    /**
     *
     * @param cow
     * @return
     */
    CowHeatSeqPO selectOneById(CowHeatSeqPO cow);

    int insert(CowHeatSeqPO cowHeatSeqPO);

    int update(CowHeatSeqPO cowHeatSeqPO);

    int delete(CowHeatSeqPO cowHeatSeqPO);
}
