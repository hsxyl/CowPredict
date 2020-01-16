package com.example.demo.convert;

import com.example.demo.model.CowHeatSeq;
import com.example.demo.model.CowHeatSeqPO;
import com.example.demo.util.OffsetDateTimeUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
public class CowHeatSeqConvert {
    public static CowHeatSeqPO seqToPO(CowHeatSeq seq) {
        CowHeatSeqPO po = new CowHeatSeqPO();
        po.setId(seq.getCowId());
        po.setStartTime(OffsetDateTimeUtil.toString(seq.getTimeSeries().startTime()));
        po.setHeats(
                Arrays.stream(seq.getTimeSeries().asArray()).
                        mapToObj(String::valueOf).
                        collect(Collectors.joining(",")));
        po.setTimeUnit(seq.getTimeSeries().timePeriod().timeUnit().toString());
        return po;
    }
}
