package com.example.demo.Service;

import com.example.demo.Mapper.CowHeatSeqMapper;
import com.example.demo.Model.CowHeatSeq;
import com.example.demo.Model.param.CreateHeatSeqParam;
import com.example.demo.Model.param.ForecastCowHeatSeqParam;
import com.example.demo.Model.param.SaveHeatSeqParam;
import com.example.demo.Model.vo.CowHeatVO;
import com.example.demo.constant.Global;
import com.example.demo.convert.CowHeatSeqConvert;
import com.example.demo.util.MyCollectionUtil;
import com.example.demo.util.OffsetDateTimeUtil;
import com.github.signaflo.timeseries.TimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2019/11/2
 */
@Service
public class CowHeatService {

    @Autowired
    CowHeatSeqMapper seqMapper;

    public CowHeatVO createCowHeatSeq(CreateHeatSeqParam param) {
        double range = param.getUpper()-param.getLower();

        List<Double> heats = new ArrayList<>();
        for(int i=0;i<param.getSum();i++) {
            heats.add(param.getLower()+Math.random()*range);
        }

        CowHeatVO cowHeatSeq = new CowHeatVO();
        cowHeatSeq.setHeats(heats);
        cowHeatSeq.setStartTime(param.getStartTime());
        cowHeatSeq.setTimeUnit(Global.TIME_UNIT.unitLength());
        return cowHeatSeq;
    }

    public boolean saveCowHeatSeq(SaveHeatSeqParam param) {
        CowHeatSeq cowHeatSeq = new CowHeatSeq();
        cowHeatSeq.setCowId(param.getCowId());

        TimeSeries timeSeries =
                TimeSeries.from(Global.TIME_UNIT,
                        OffsetDateTimeUtil.fromLong(param.getStartTime()),
                        MyCollectionUtil.listToArray(param.getHeats()));
        cowHeatSeq.setTimeSeries(timeSeries);
        seqMapper.insert(CowHeatSeqConvert.seqToPO(cowHeatSeq));
        return true;
    }

    public  forecastCowHeatSeq(ForecastCowHeatSeqParam param){
        
    }

}
