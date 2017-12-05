package com.lvmama.captcha.service;

import com.lvmama.captcha.model.BusinessType;
import com.lvmama.captcha.model.CaptchaRule;
import com.lvmama.captcha.vo.CaptchaRuleVO;

import java.util.List;
import java.util.Map;

/**
 * Created by qianjiangtao on 2016/11/23.
 */

public interface CaptchaRuleService {
    /**
     * 保存验证码规则
     *
     * @param captchaRule
     * @return
     */
    int saveCaptchaRule(CaptchaRule captchaRule);

    /**
     * 带分页查询
     *
     * @param params
     * @return
     */
    List<CaptchaRule> queryCaptchaRules(Map<String, Object> params);

    /**
     * 根据条件查询验证码规则数量
     *
     * @param params 业务类型  安全级别
     * @return
     */
    Long queryCaptchaRulesCount(Map<String, Object> params);

    /**
     * 根据id查询验证码规则
     *
     * @param captchaRuleId
     * @return
     */
    CaptchaRuleVO queryCaptchaRuleById(Long captchaRuleId);

    /**
     * 根据id删除验证码规则
     *
     * @param captchaRuleId
     * @return
     */
    int deleteCaptchaRuleById(Long captchaRuleId);

    /**
     * 根据业务类型和安全级别查询验证码规则是否存在
     *
     * @param businessCode
     * @param secureLevel
     * @return
     */
    Long queryByCodeAndSecureLevel(String businessCode, String secureLevel);

    List<BusinessType> queryBusinessAndLevel();

    boolean hasDefaultCaptcha(String defaultLevel,String bizCode);
}
