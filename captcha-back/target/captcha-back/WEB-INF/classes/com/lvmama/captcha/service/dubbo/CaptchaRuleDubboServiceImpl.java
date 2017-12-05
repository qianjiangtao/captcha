package com.lvmama.captcha.service.dubbo;

import com.lvmama.captcha.dao.BusinessTypeDao;
import com.lvmama.captcha.dao.CaptchaRuleDao;
import com.lvmama.captcha.dao.RiskscoreSecurelevelDao;
import com.lvmama.captcha.dto.CaptchaRuleDTO;
import com.lvmama.captcha.dto.RiskscoreSecurelevelDTO;
import com.lvmama.captcha.service.CaptchaRuleDubboService;
import com.lvmama.captcha.dto.BusinessTypeDTO;
import com.lvmama.captcha.util.CaptchaUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by qianjiangtao on 2016/11/29.
 */
@Service
public class CaptchaRuleDubboServiceImpl implements CaptchaRuleDubboService {
    @Resource
    private BusinessTypeDao businessTypeDao;
    @Resource
    private CaptchaRuleDao captchaRuleDao;
    @Resource
    private RiskscoreSecurelevelDao riskscoreSecurelevelDao;

    /**
     * 根据业务编码和安全级别查询验证码规则数据
     *
     * @param businessCode 业务编码
     * @param secureLevel  安全级别
     * @return
     */
    @Override
    public CaptchaRuleDTO queryCaptchaRuleByCodeAndLevel(String businessCode, String secureLevel) {
        return CaptchaUtil.formatCaptchaRuleDTO(captchaRuleDao.queryByCodeAndSecureLevel(businessCode, secureLevel));
    }

    /**
     * 查询所有业务code
     *
     * @return
     */
    @Override
    public List<String> queryAllBusinessCode() {
        return businessTypeDao.queryAllBusinessCode();
    }

    /**
     * 根据业务编码查询业务信息
     *
     * @param businessCode
     * @return
     */
    @Override
    public BusinessTypeDTO queryBusinessTypeByCode(String businessCode) {
        return businessTypeDao.queryBusinessTypeByCode(businessCode);
    }

    /**
     * 根据业务code查询分控设置
     *
     * @param businessCode
     * @return
     */
    @Override
    public List<RiskscoreSecurelevelDTO> queryRiskscoreSecurelevelByCode(String businessCode) {
        return riskscoreSecurelevelDao.queryRiskscoreSecurelevelCode(businessCode);
    }

    /**
     * 查询业务code和验证码级别(默认级别和设置的级别)
     *
     * @return 返回的map以code为key value是以默认级别为第一个元素的list
     */
    @Override
    public Map<String, List<String>> queryCodeAndLevels() {

        Map<String, List<String>> map = new HashMap<>();
        List<BusinessTypeDTO> bizDTOs = businessTypeDao.queryCodeAndDefaultLevel();

        if (bizDTOs != null && bizDTOs.size() > 0) {
            for (BusinessTypeDTO bizDTO : bizDTOs) {
                //将默认级别放入list第一位
                List<String> values = new ArrayList<>();
                values.add(bizDTO.getDefaultLevel());

                //查询验证码设置的所有级别
                List<String> levels = captchaRuleDao.queryLevelsByCode(bizDTO.getCode());
                for (String level : levels) {
                    if (!bizDTO.getDefaultLevel().equals(level)) {
                        values.add(level);
                    }
                }
                //放入map
                map.put(bizDTO.getCode(), values);
            }
        }
        return map;
    }

    /**
     * 查询业务code和默认级别
     *
     * @return
     */
    @Override
    public Map<String, String> queryCodeAndDefaultLevel() {

        Map<String, String> map = new HashMap<>();
        List<BusinessTypeDTO> bizDTOs = businessTypeDao.queryCodeAndDefaultLevel();
        if (bizDTOs != null && bizDTOs.size() > 0) {
            for (BusinessTypeDTO bizDTO : bizDTOs) {
                map.put(bizDTO.getCode(), bizDTO.getDefaultLevel());
            }
        }
        return map;
    }
}
