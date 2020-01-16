package com.example.demo.check;

import com.example.demo.util.LocalDateTimeUtil;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/11
 */
public class ParamCheck {
    public static void checkTimeRangeAndData(LocalDateTime beginDateTime,
                                             LocalDateTime endDateTime,
                                             Duration duration,
                                             List<Double> valueList) {
        long size = (LocalDateTimeUtil.localDateTimeToSeconds(endDateTime)-
                LocalDateTimeUtil.localDateTimeToSeconds(beginDateTime))/duration.getSeconds()+1;
        Assert.isTrue(size==valueList.size(),
                String.format("开始结束时间与值列表个数不匹配,begin:%s,end:%s,timeDataList.size():%d",beginDateTime,endDateTime,valueList.size()));
    }
}
