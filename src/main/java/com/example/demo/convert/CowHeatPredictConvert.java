package com.example.demo.convert;

import com.example.demo.model.bo.CowHeatPredictBO;
import com.example.demo.model.vo.CowHeatPredictVO;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/1/17
 */
public class CowHeatPredictConvert {
    public static CowHeatPredictVO convertToVO(CowHeatPredictBO cowHeatPredictBO) {
        return new CowHeatPredictVO(
                cowHeatPredictBO.getBeginTime(),
                cowHeatPredictBO.getEndTime(),
                cowHeatPredictBO.getCowHeat());
    }
}
