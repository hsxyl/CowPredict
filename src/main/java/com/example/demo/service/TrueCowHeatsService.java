package com.example.demo.service;

import com.example.demo.model.vo.TrueCowHeatVO;
import com.example.demo.sql.entity.TrueCowTemperature;
import com.example.demo.sql.entity.TrueCowTemperatureExample;
import com.example.demo.sql.mapper.TrueCowTemperatureMapper;
import com.example.demo.util.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/11
 */
@Service
@Slf4j
public class TrueCowHeatsService {

    @Autowired
    TrueCowTemperatureMapper trueCowTemperatureMapper;

    public boolean insertOrUpdateTimePoint(int cowId, LocalDateTime localDateTime, Double value) {
        // 舍弃掉分钟和秒（都置为0）
        localDateTime = LocalDateTimeUtil.dropMinuteAndSecond(localDateTime);

        TrueCowTemperature trueCowTemperature = new TrueCowTemperature();
        trueCowTemperature.setCowId(cowId);
        trueCowTemperature.setTime(localDateTime);
        trueCowTemperature.setValue(value);

        TrueCowTemperatureExample example = new TrueCowTemperatureExample();
        example.or().andCowIdEqualTo(cowId).andTimeEqualTo(localDateTime);
        if(CollectionUtils.isEmpty(trueCowTemperatureMapper.selectByExample(example))) {
            trueCowTemperatureMapper.insert(trueCowTemperature);
        } else {
            trueCowTemperatureMapper.updateByExampleSelective(trueCowTemperature,example);
        }
        return true;
    }

    /**
     * 给王侠师兄保存奶牛体温的专用接口
     * @param value 参数就一个value,奶牛id固定，时间点生成
     * @return
     */
    public boolean saveTruCow(Double value) {
        TrueCowTemperature trueCowTemperature = new TrueCowTemperature();
        trueCowTemperature.setTime(LocalDateTime.now());
        trueCowTemperature.setValue(value);
        trueCowTemperature.setCowId(999);
        return trueCowTemperatureMapper.insert(trueCowTemperature)==1;
    }

    public boolean insertOrUpdateTimeRange(int cowId,
                                           LocalDateTime beginTime,
                                           LocalDateTime endTime,
                                           Duration duration,
                                           List<Double> valueList) {
        for(Double value : valueList) {
            insertOrUpdateTimePoint(cowId, beginTime,value);
            beginTime = beginTime.plusSeconds(duration.getSeconds());
        }
        return true;
    }
    public TrueCowHeatVO queryTimeRange(int cowId,
                                        LocalDateTime beginTime,
                                        LocalDateTime endTime,
                                        Duration duration) {
        TrueCowTemperatureExample example = new TrueCowTemperatureExample();
        example.or().andTimeBetween(beginTime,endTime).andCowIdEqualTo(cowId);
        List<TrueCowTemperature> trueCowTemperatureList = trueCowTemperatureMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(trueCowTemperatureList)) {
            throw new RuntimeException("没有该时间区间的数据");
        }
        trueCowTemperatureList = trueCowTemperatureList.stream().sorted(Comparator.comparing(TrueCowTemperature::getTime)).collect(Collectors.toList());


        return new TrueCowHeatVO()
                .setBeginTime(trueCowTemperatureList.get(0).getTime())
                .setEndTime(trueCowTemperatureList.get(trueCowTemperatureList.size()-1).getTime())
                .setHeatList(trueCowTemperatureList
                        .stream()
                        .map(TrueCowTemperature::getValue)
                        .collect(Collectors.toList()));
    }
}
