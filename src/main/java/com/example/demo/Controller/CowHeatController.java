package com.example.demo.Controller;

import com.example.demo.Model.Cow;
import com.example.demo.Model.param.CreateHeatSeqParam;
import com.example.demo.Model.param.ForecastCowHeatSeqParam;
import com.example.demo.Model.param.SaveHeatSeqParam;
import com.example.demo.Service.CowHeatSeqService;
import com.example.demo.Service.CowService;
import com.example.demo.result.Failures;
import com.example.demo.result.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xushenbao
 * @desc 奶牛体温相关接口
 * @create 2019/11/2
 */
@RestController
@RequestMapping("/cow_heat")
@Slf4j
@CrossOrigin
public class CowHeatController {

    @Autowired
    CowHeatSeqService cowHeatService;

    @Autowired
    CowService cowService;

    @GetMapping("/create")
    public ResultVO createCowHeatSeq(@Valid CreateHeatSeqParam param) {
        try {
            return ResultVO.success(cowHeatService.createCowHeatSeq(param));
        }catch (Exception ex) {
            log.error("Fail to creataHeatSeq,param is [{}],exception:",param,ex);
            return Failures.SERVER_ERROR;
        }
    }

    @PostMapping("/save")
    public ResultVO saveCowHeatSeq(@RequestBody SaveHeatSeqParam param) {

        try {
            Cow cow = new Cow(param.getCowId());
            if(CollectionUtils.isEmpty(cowService.queryCow(cow))) {
                return Failures.COWNAME_UNEXISTS;
            }

                return ResultVO.success(cowHeatService.saveCowHeatSeq(param));
        } catch (Exception ex) {
            log.error("Fail to saveHeatSeq,param is [{}],exception:",param,ex);
            return Failures.SERVER_ERROR;
        }
    }

    @PostMapping("/forecast")
    public ResultVO forecastCowHeatSeq(@RequestBody ForecastCowHeatSeqParam param) {
        try {
            return ResultVO.success(cowHeatService.forecastSeq(param));
        } catch (Exception ex) {
            log.error("Fail to forecastCowHeatSeq,param is [{}],exception:",param,ex);
            return Failures.SERVER_ERROR;
        }
    }

    @PostMapping("/test")
    public ResultVO testCowHeatSeq(@RequestBody ForecastCowHeatSeqParam param) {
        try {
            return ResultVO.success(cowHeatService.forecastCowHeatSeq(param));
        } catch (Exception ex) {
            log.error("Fail to forecastCowHeatSeq,param is [{}],exception:",param,ex);
            return Failures.SERVER_ERROR;
        }
    }
}
