package com.example.demo.mapper;

import com.example.demo.Model.Cow;
import com.example.demo.Model.CowHeatSeq;
import com.example.demo.Model.CowHeatSeqPO;
import com.example.demo.Model.PageContent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Mapper
@Component
public interface CowHeatSeqMapper {

    CowHeatSeqPO selectOneById(CowHeatSeqPO cow);

    int insert(CowHeatSeqPO cowHeatSeqPO);

    int update(CowHeatSeqPO cowHeatSeqPO);

    int delete(CowHeatSeqPO cowHeatSeqPO);
}
