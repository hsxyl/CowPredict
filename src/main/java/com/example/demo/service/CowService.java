package com.example.demo.service;

import com.example.demo.model.PageContent;
import com.example.demo.model.param.CowPageParam;
import com.example.demo.mapper.MyCowMapper;
import com.example.demo.model.Cow;
import com.example.demo.result.RestPageResultVO;
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
    MyCowMapper myCowMapper;

    public Cow addCow(Cow cow) {
         myCowMapper.insert(cow);
         return myCowMapper.selectOne(cow);
    }

    public List<Cow> queryCow(Cow cow) {
        return myCowMapper.selectByCowAndPage(cow,null);
    }

    public long update(Cow cow) {
        return myCowMapper.update(cow);
    }
    public RestPageResultVO<Cow> queryCowByPage(CowPageParam cowPageParam) {
        Cow cow = new Cow();
        cow.setName(cowPageParam.getName());
        int pageSize = 10;
        PageContent pageContent = new PageContent(cowPageParam.getPageNum(),pageSize);
        int total = myCowMapper.count(cow);
        List<Cow> cowList = myCowMapper.selectByCowAndPage(cow,pageContent);
        return RestPageResultVO.success(cowList,total,pageContent.getPageNum());
    }
    public int delete(List<Integer> idList) {
        return myCowMapper.deteleByIds(idList);
    }


}
