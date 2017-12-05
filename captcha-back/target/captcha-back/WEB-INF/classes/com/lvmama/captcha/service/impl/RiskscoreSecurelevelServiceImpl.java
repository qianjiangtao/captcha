package com.lvmama.captcha.service.impl;

import com.lvmama.captcha.dao.RiskscoreSecurelevelDao;
import com.lvmama.captcha.model.RiskscoreSecurelevel;
import com.lvmama.captcha.service.RiskscoreSecurelevelService;
import com.lvmama.captcha.vo.RiskscoreSecurelevelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/23.
 */
@Service("riskscoreSecurelevelService")
public class RiskscoreSecurelevelServiceImpl implements RiskscoreSecurelevelService {

    @Autowired
    private RiskscoreSecurelevelDao riskscoreSecurelevelDao;

    @Override
    public int insert(RiskscoreSecurelevel riskscoreSecurelevel) {
        return riskscoreSecurelevelDao.insert(riskscoreSecurelevel);
    }

    @Override
    public int updateByPrimaryKeySelective(RiskscoreSecurelevel riskscoreSecurelevel) {
        return riskscoreSecurelevelDao.updateByPrimaryKeySelective(riskscoreSecurelevel);
    }

    @Override
    public Boolean decideById(Long id ) {
        return riskscoreSecurelevelDao.decideById(id);
    }

    @Override
    public int updateByBusinessCodeSelective(RiskscoreSecurelevel riskscoreSecurelevel) {
        return riskscoreSecurelevelDao.updateByBusinessCodeSelective(riskscoreSecurelevel);
    }

    @Override
    public Long selectCountByCondition(Map map) {
        return riskscoreSecurelevelDao.selectCountByCondition(map);
    }

    @Override
    public List<RiskscoreSecurelevelVo> selectRiskscoreSecurelevelByCondition(Map map) {
        return riskscoreSecurelevelDao.selectRiskscoreSecurelevelByCondition(map);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return riskscoreSecurelevelDao.deleteByPrimaryKey(id);
    }

    @Override
    public RiskscoreSecurelevelVo selectRiskscoreSecurelevelById(Long id) {
        return riskscoreSecurelevelDao.selectRiskscoreSecurelevelById(id);
    }

    @Override
    public Long selectIdByCondition(String businessCode,String secureLevel) {
        return riskscoreSecurelevelDao.selectIdByCondition(businessCode,secureLevel);
    }
}
