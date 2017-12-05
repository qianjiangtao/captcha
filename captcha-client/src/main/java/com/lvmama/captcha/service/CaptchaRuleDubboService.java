package com.lvmama.captcha.service;

import com.lvmama.captcha.dto.CaptchaRuleDTO;
import com.lvmama.captcha.dto.BusinessTypeDTO;
import com.lvmama.captcha.dto.RiskscoreSecurelevelDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by qianjiangtao on 2016/11/29.
 * 验证码接口 供captcha_front获取验证码规则服务
 */
public interface CaptchaRuleDubboService {

    /**
     * 根据业务编码,安全级别查询验证码规则
     *
     * @param businessCode 业务类型
     * @param secureLevel  安全级别
     * @return
     */
    public CaptchaRuleDTO queryCaptchaRuleByCodeAndLevel(String businessCode, String secureLevel);

    /**
     * 根据业务编码查询所有业务code
     *
     * @return
     */
    public List<String> queryAllBusinessCode();

    /**
     * 根据业务编码查询业务数据
     *
     * @param businessCode
     * @return 默认安全等级, 是否接入风控, 应用标识, 事件标识
     */
    public BusinessTypeDTO queryBusinessTypeByCode(String businessCode);

    /**
     * 根据业务code查询分控级别
     *
     * @param businessCode
     * @return
     */
    public List<RiskscoreSecurelevelDTO> queryRiskscoreSecurelevelByCode(String businessCode);

    /**
     * 查询业务code和验证码级别
     *
     * @return
     */
    public Map<String, List<String>> queryCodeAndLevels();

    /**
     * 查询业务code和默认级别
     *
     * @return
     */
    public Map<String, String> queryCodeAndDefaultLevel();
}
