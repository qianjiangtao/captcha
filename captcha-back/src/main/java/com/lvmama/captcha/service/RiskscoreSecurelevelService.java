package com.lvmama.captcha.service;

import com.lvmama.captcha.model.RiskscoreSecurelevel;
import com.lvmama.captcha.vo.RiskscoreSecurelevelVo;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/23.
 */
public interface RiskscoreSecurelevelService {

    int insert(RiskscoreSecurelevel riskscoreSecurelevel);

    int updateByPrimaryKeySelective(RiskscoreSecurelevel riskscoreSecurelevel);

    /*根据id查找数据库是否已有RiskscoreSecurelevel*/
    Boolean decideById(Long id );

    int updateByBusinessCodeSelective(RiskscoreSecurelevel riskscoreSecurelevel);

    Long selectCountByCondition(Map map);

    List<RiskscoreSecurelevelVo> selectRiskscoreSecurelevelByCondition(Map map);

    int deleteByPrimaryKey(Long id);

    RiskscoreSecurelevelVo selectRiskscoreSecurelevelById(Long id);

    Long selectIdByCondition(String businessCode,String secureLevel);
}
