package com.example.demo.jobs;

import com.example.demo.constant.CowHeatTemplate;
import com.example.demo.service.TrueCowHeatsService;
import com.example.demo.sql.entity.Cow;
import com.example.demo.sql.entity.CowExample;
import com.example.demo.sql.entity.TrueCowTemperatureExample;
import com.example.demo.sql.mapper.CowMapper;
import com.example.demo.sql.mapper.TrueCowTemperatureMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/11
 */
@Slf4j
@Component
public class RandomCowHeat {

    @Autowired
    CowMapper cowMapper;
    @Autowired
    TrueCowHeatsService trueCowHeatsService;
    @Autowired
    TrueCowTemperatureMapper trueCowTemperatureMapper;

    // 每两小时执行一次
    @Scheduled(cron = "0 0 0/2 * * *")
    public void createCowHeat2Hour() {
        try {
            CowExample cowExample = new CowExample();
            cowExample.or().andIdIsNotNull();
            List<Cow> cowList = cowMapper.selectByExample(cowExample);
            // 当前时间
            LocalDateTime now = LocalDateTime.now();
            // 创建时间点的时间，去除当前时间的分，秒
            LocalDateTime createTime = LocalDateTime.of(now.getYear(),
                    now.getMonth(),
                    now.getDayOfMonth(),
                    now.getHour(),0,0);
            for (Cow cow : cowList) {
                trueCowHeatsService.insertOrUpdateTimePoint(
                        cow.getId(),
                        createTime,
                        CowHeatTemplate.generatePoint(createTime));
            }
        } catch (Exception e) {
            log.error("Fail to do Job<createCowHeat2Hour>,cause:",e);
        }
    }
    @Scheduled(cron = "0 0 0 1/1 * ? ")
    public void deleteTimeOut() {
        try {
            TrueCowTemperatureExample example = new TrueCowTemperatureExample();
            example.or().andTimeLessThan(LocalDateTime.now().minusWeeks(6));
            trueCowTemperatureMapper.deleteByExample(example);
        } catch (Exception e) {
            log.error("Fail to do Job<deleteTimeOut>,cause:",e);
        }
    }


}
