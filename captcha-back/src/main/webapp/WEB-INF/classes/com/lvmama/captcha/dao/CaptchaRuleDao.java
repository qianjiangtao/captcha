package com.lvmama.captcha.dao;

import org.springframework.stereotype.Repository;

import com.lvmama.captcha.dao.base.MysqlDaoSupport;
import com.lvmama.captcha.model.CaptchaRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CaptchaRuleDao extends MysqlDaoSupport {
    public CaptchaRuleDao() {
        super("com.lvmama.captcha.dao.CaptchaRule");
    }

    public int insertCaptchaRule(CaptchaRule captchaRule) {
        return super.insert("insertCaptchaRule", captchaRule);
    }

    public int updateCaptchaRuleById(CaptchaRule captchaRule) {
        return super.update("updateCaptchaRuleById", captchaRule);
    }

    public List<CaptchaRule> queryCaptchaRules(Map<String, Object> params) {
        return super.queryForList("queryCaptchaRules", params);
    }

    public Long queryCaptchaRulesCount(Map<String, Object> params) {
        return super.get("queryCaptchaRulesCount", params);
    }

    public CaptchaRule queryCaptchaRuleById(Long captchaRuleId) {
        return super.get("queryCaptchaRuleById", captchaRuleId);
    }

    public int deleteCaptchaRuleById(Long captchaRuleId) {
        return super.delete("deleteCaptchaRuleById", captchaRuleId);
    }

    public CaptchaRule queryByCodeAndSecureLevel(String businessCode, String secureLevel) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("businessCode", businessCode);
        params.put("secureLevel", secureLevel);
        return super.get("queryByCodeAndSecureLevel", params);
    }

    public List<String> queryLevelsByCode(String businessCode){
        return super.queryForList("queryLevelsByCode",businessCode);
    }
}