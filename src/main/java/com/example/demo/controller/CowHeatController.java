package com.example.demo.controller;

import com.example.demo.check.ParamCheck;
import com.example.demo.constant.CowHeatTemplate;
import com.example.demo.constant.Global;
import com.example.demo.convert.CowHeatPredictConvert;
import com.example.demo.model.Cow;
import com.example.demo.model.TimeSequence;
import com.example.demo.model.param.CreateHeatSeqParam;
import com.example.demo.model.param.ForecastCowHeatSeqParam;
import com.example.demo.model.param.JudgeStatusParam;
import com.example.demo.model.param.PredictCowHeatParam;
import com.example.demo.model.param.SaveHeatSeqParam;
import com.example.demo.model.param.TrueCowHeatCreateParam;
import com.example.demo.model.vo.TrueCowHeatVO;
import com.example.demo.result.Failures;
import com.example.demo.result.ResultVO;
import com.example.demo.service.CowHeatSeqService;
import com.example.demo.service.CowService;
import com.example.demo.service.TrueCowHeatsService;
import com.example.demo.util.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author xushenbao
 * @desc 奶牛体温相关接口
 * @create 2019/11/2
 */
@RestController
@RequestMapping("/cow_heat")
@CrossOrigin(origins = "*")
@Slf4j
public class CowHeatController {

    @Autowired
    CowHeatSeqService cowHeatService;
    @Autowired
    CowService cowService;
    @Autowired
    TrueCowHeatsService trueCowHeatsService;

    @GetMapping("/create")
    public ResultVO createCowHeatSeq(@Valid CreateHeatSeqParam param) {
        try {
            return ResultVO.success(cowHeatService.createCowHeatSeq(param));
        } catch (Exception ex) {
            log.error("Fail to creataHeatSeq,param is [{}],exception:", param, ex);
            return Failures.SERVER_ERROR;
        }
    }

    @PostMapping("/save")
    public ResultVO saveCowHeatSeq(@RequestBody SaveHeatSeqParam param) {

        try {
            Cow cow = new Cow(param.getCowId());
            if (CollectionUtils.isEmpty(cowService.queryCow(cow))) {
                return Failures.COWNAME_UNEXISTS;
            }

            return ResultVO.success(cowHeatService.saveCowHeatSeq(param));
        } catch (Exception ex) {
            log.error("Fail to saveHeatSeq,param is [{}],exception:", param, ex);
            return Failures.SERVER_ERROR;
        }
    }

    /**
     * 需要做一些误差分析，所以这个不是真正的预测，理解为测试集
     *
     * @param param
     * @return
     */
    @PostMapping("/forecast")
    public ResultVO forecastCowHeatSeq(@RequestBody ForecastCowHeatSeqParam param) {
        try {
            return ResultVO.success(cowHeatService.forecastSeq(param));
        } catch (Exception ex) {
            log.error("Fail to forecastCowHeatSeq,param is [{}],exception:", param, ex);
            return Failures.SERVER_ERROR;
        }
    }

    /**
     * 预测
     *
     * @return
     */
    @PostMapping("/predict")
    public ResultVO predictCowHeatSeq(@RequestBody PredictCowHeatParam param) {
        try {
            LocalDateTime beginTime = LocalDateTime.of(
                    param.getBeginTime().getYear(),
                    param.getBeginTime().getMonth(),
                    param.getBeginTime().getDayOfMonth(),
                    param.getBeginTime().getHour(),0,0);
            TimeSequence timeSequence = cowHeatService.getOriginValueForPredict(
                    param.getCowId(),
                    beginTime);
            return ResultVO.success(
                    CowHeatPredictConvert.convertToVO(
                            cowHeatService.predictCowHeat(
                                    timeSequence.getTimeDataList(),
                                    param.getPredictNum(),
                                    beginTime)));
        } catch (Exception e) {
            log.error("Fail to predictCowHeatSeq,param is [{}],exception:", param, e);
            return ResultVO.fail(e);
        }
    }


    @GetMapping("/status")
    public ResultVO status() {
        return ResultVO.success(true);
    }

    /**
     * 给王侠师兄用的接口
     *
     * @param value
     * @return
     */
    @GetMapping("/true_cow")
    public ResultVO saveTrueCowHeat(@RequestParam(value = "value") Double value) {
        try {
            return ResultVO.success(trueCowHeatsService.saveTruCow(value));
        } catch (Exception e) {
            log.error("Fail to save trueCow,value is [{}],exception:", value, e);
            return Failures.SERVER_ERROR;
        }
    }

    @PostMapping("/create_true_cow")
    public ResultVO createTrueCowHeat(@RequestBody TrueCowHeatCreateParam param) {
        try {
            if (CollectionUtils.isEmpty(param.getHeatList())) {
                param.setHeatList(CowHeatTemplate.generatePointRange(
                        param.getBeginTime(),
                        param.getEndTime(),
                        Global.DURATION));
            }
            ParamCheck.checkTimeRangeAndData(
                    param.getBeginTime(),
                    param.getEndTime(),
                    Global.DURATION,
                    param.getHeatList());
            return ResultVO.success(trueCowHeatsService.insertOrUpdateTimeRange(param.getCowId(),
                    param.getBeginTime(),
                    param.getEndTime(),
                    Global.DURATION,
                    param.getHeatList()));
        } catch (Exception e) {
            log.error("Fail to create trueCow,param is [{}],ex:", param, e);
            return ResultVO.fail(e);
        }
    }

    @GetMapping("/query_true_cow_heat")
    public ResultVO queryTrueCowHeat(@RequestParam(value = "cow_id") int cowId,
                                     @RequestParam(value = "begin_time") String beginTimeStr,
                                     @RequestParam(value = "end_time") String endTimeStr) {
        try {
            TrueCowHeatVO trueCowHeatVO = trueCowHeatsService.queryTimeRange(
                    cowId,
                    LocalDateTimeUtil.string2LocalDateTime(beginTimeStr),
                    LocalDateTimeUtil.string2LocalDateTime(endTimeStr),
                    Global.DURATION);
            return ResultVO.success(trueCowHeatVO);
        } catch (Exception e) {
            log.error("Fail to queryTrueCowHeat,cowId is [{}],beginTime is [{}],endTime is [{}],expection:", cowId, beginTimeStr, endTimeStr, e);
            return ResultVO.fail(e);
        }
    }


    @PostMapping("/test")
    public ResultVO testCowHeatSeq(@RequestBody ForecastCowHeatSeqParam param) {
        try {
            return ResultVO.success(cowHeatService.forecastCowHeatSeq(param));
        } catch (Exception ex) {
            log.error("Fail to forecastCowHeatSeq,param is [{}],exception:", param, ex);
            return Failures.SERVER_ERROR;
        }
    }

    /**
     * todo 还不知道检测规则
     *
     * @param param
     * @return
     */
    @PostMapping("/judge_status")
    public ResultVO judgeStatus(@RequestBody JudgeStatusParam param) {
        try {
            return ResultVO.success(null);
        } catch (Exception e) {
            log.error("Fail to judgeStatus,param is [{}],exception:", param, e);
            return ResultVO.fail(e);
        }
    }

}
