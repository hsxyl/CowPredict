package com.example.demo.Service;

import com.example.demo.mapper.CowMapper;
import com.example.demo.Model.Cow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/1
 */
@Service
public class CowService {

    @Autowired
    CowMapper cowMapper;

    public Cow addCow(Cow cow) {
         cowMapper.insert(cow);
         return cowMapper.selectOne(cow);
    }

    public List<Cow> queryCow(Cow cow) {
        return cowMapper.selectByCowAndPage(cow,null);
    }

    public long update(Cow cow) {
        return cowMapper.update(cow);
    }



}
