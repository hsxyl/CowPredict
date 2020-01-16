package com.example.demo.mapper;

import com.example.demo.model.Cow;
import com.example.demo.model.PageContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/1
 */
@Mapper
@Component
public interface MyCowMapper {

    /**
     *
     * @param id
     * @return
     */
    Cow selectById(Long id);

    List<Cow> selectByCowAndPage(@Param("cow") Cow cow, @Param("page") PageContent page);

    Cow selectOne(Cow cow);

    int insert(Cow cow);

    long update(Cow cow);

    long delete(Cow cow);

    int deteleByIds(@Param("idList") List<Integer> idList);

    int count(Cow cow);

}
