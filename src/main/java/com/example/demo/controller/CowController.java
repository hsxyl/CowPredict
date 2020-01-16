package com.example.demo.controller;

import com.example.demo.model.Cow;
import com.example.demo.model.param.CowPageParam;
import com.example.demo.service.CowService;
import com.example.demo.result.Failures;
import com.example.demo.result.RestPageResultVO;
import com.example.demo.result.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/1
 */
@RestController
@RequestMapping("/cow")
@CrossOrigin(origins = "*")
@Slf4j
public class CowController {

    @Autowired
    CowService cowService;

    @GetMapping("/query")
    public ResultVO queryCow(@Valid Cow cow) {
        List<Cow> cowList ;
        try {
            cowList = cowService.queryCow(cow);
        } catch (Exception ex) {
            log.error("Fail to queryCow,cow is [{}],exception:",cow,ex);
            return Failures.SERVER_ERROR;
        }
        return ResultVO.success(cowList);
    }

    @PostMapping("/insert")
    public ResultVO insertCow(@Valid @RequestBody Cow cow) {
        try {
            if(!CollectionUtils.isEmpty(cowService.queryCow(cow))) {
                return Failures.COWNAME_EXISTS;
            }
            return ResultVO.success(cowService.addCow(cow));
        } catch (Exception ex) {
            log.error("Fail to insertUser,cow is [{}],exception:",cow,ex);
            return Failures.SERVER_ERROR;
        }
    }

    @PostMapping("/update")
    public ResultVO updateCow(@Valid @RequestBody Cow cow) {
        Assert.isTrue(cow.getId()!=null,"奶牛id不为空");
        try {
            return ResultVO.success(cowService.update(cow));
        } catch(Exception ex) {
            log.error("Fail to updateCow,cow is [{}],exception:",cow,ex);
            return Failures.SERVER_ERROR;
        }
    }

    @GetMapping("/query_page")
    public RestPageResultVO<Cow> getCowByPage(CowPageParam cowPageParam) {
            return cowService.queryCowByPage(cowPageParam);
    }

    @GetMapping("/batch_remove")
    public ResultVO deleteCow(@Valid String ids) {
        List<Integer> idList = Arrays.stream(ids.trim().split(","))
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());
        try {
            return ResultVO.success(cowService.delete(idList));
        } catch (Exception e) {
            log.error("Fail to delete idList:[{}]",idList,e);
            return ResultVO.fail(500,e.getMessage());
        }
    }

}
