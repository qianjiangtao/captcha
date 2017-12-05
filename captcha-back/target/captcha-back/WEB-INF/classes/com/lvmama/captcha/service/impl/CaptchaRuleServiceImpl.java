package com.lvmama.captcha.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lvmama.captcha.dao.BusinessTypeDao;
import com.lvmama.captcha.dao.CaptchaRuleDao;
import com.lvmama.captcha.model.BusinessType;
import com.lvmama.captcha.model.CaptchaRule;
import com.lvmama.captcha.service.CaptchaRuleService;
import com.lvmama.captcha.util.CaptchaUtil;
import com.lvmama.captcha.vo.CaptchaRuleVO;
import com.lvmama.captcha.vo.FontVO;
import com.lvmama.captcha.vo.NoiseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.List;

/**
 * Created by qianjiangtao on 2016/11/23.
 */
@Service
public class CaptchaRuleServiceImpl implements CaptchaRuleService {

    @Resource
    private CaptchaRuleDao captchaRuleDao;
    @Resource
    private BusinessTypeDao businessTypeDao;

    /**
     * 保存或更新验证码规则
     *
     * @param captchaRule
     * @return
     */
    @Override
    public int saveCaptchaRule(CaptchaRule captchaRule) {

        int isSuccess = 0;
        if (captchaRule.getId() == null) {
            captchaRule.setCreateTime(new Date());
            isSuccess = captchaRuleDao.insertCaptchaRule(captchaRule);
        } else {
            captchaRule.setUpdateTime(new Date());
            isSuccess = captchaRuleDao.updateCaptchaRuleById(captchaRule);
        }
        return isSuccess;
    }

    /**
     * 根据条件查询验证码规则数量
     *
     * @param params
     * @return
     */
    @Override
    public List<CaptchaRule> queryCaptchaRules(Map<String, Object> params) {
        return captchaRuleDao.queryCaptchaRules(params);
    }

    /**
     * 查询符合条件的验证码规则数量
     *
     * @param params 业务类型  安全级别
     * @return
     */
    @Override
    public Long queryCaptchaRulesCount(Map<String, Object> params) {
        return captchaRuleDao.queryCaptchaRulesCount(params);
    }

    /**
     * 根据id查询验证码规则
     *
     * @param captchaRuleId
     * @return
     */
    @Override
    public CaptchaRuleVO queryCaptchaRuleById(Long captchaRuleId) {
        CaptchaRule captchaRule = captchaRuleDao.queryCaptchaRuleById(captchaRuleId);
        return formatCaptchaRuleVO(captchaRule);
    }

    /**
     * 根据id删除某条验证码规则
     *
     * @param captchaRuleId
     * @return
     */
    @Override
    public int deleteCaptchaRuleById(Long captchaRuleId) {
        return captchaRuleDao.deleteCaptchaRuleById(captchaRuleId);
    }

    /**
     * 根据业务类型和安全级别查询数量
     *
     * @param businessCode
     * @param secureLevel
     * @return
     */
    public Long queryByCodeAndSecureLevel(String businessCode, String secureLevel) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("businessCode", businessCode);
        params.put("secureLevel", secureLevel);
        return captchaRuleDao.queryCaptchaRulesCount(params);
    }

    /**
     * 业务编码,安全级别联动信息
     *
     * @return
     */
    @Override
    public List<BusinessType> queryBusinessAndLevel() {
        return businessTypeDao.selectCondition();
    }

    /**
     * 将CaptchaRule 转化为 CaptchaRuleVO
     *
     * @param captchaRule
     * @return
     */
    public CaptchaRuleVO formatCaptchaRuleVO(CaptchaRule captchaRule) {
        CaptchaRuleVO captchaRuleVO = new CaptchaRuleVO();
        BeanUtils.copyProperties(captchaRule, captchaRuleVO);
        //字符长度
        captchaRuleVO.setCharLengths(JSON.parseArray(captchaRule.getCharLength(), Integer.class));
        //字体
        captchaRuleVO.setFonts(JSON.parseArray(captchaRule.getFont(), FontVO.class));
        //背景渐变色
        captchaRuleVO.setBgColors(CaptchaUtil.toHexColorList(JSON.parseArray(captchaRule.getBgColor(), Integer.class)));
        //字符颜色
        captchaRuleVO.setCharColors(CaptchaUtil.toHexColorList(JSON.parseArray(captchaRule.getCharColor(), Integer.class)));
        //干扰线
        captchaRuleVO.setNoises(JSON.parseArray(captchaRule.getNoise(), NoiseVO.class));
        //背景类型
        JSONObject json = (JSONObject) JSON.parse(captchaRule.getBgType());
        captchaRuleVO.setShadow((Integer) json.get("shadow"));
        captchaRuleVO.setStretch((Integer) json.get("stretch"));
        captchaRuleVO.setFishEye((Integer) json.get("fishEye"));
        captchaRuleVO.setRipple((Integer) json.get("ripple"));
        return captchaRuleVO;
    }

    public boolean hasDefaultCaptcha(String defaultLevel,String bizCode){
        Map<String,Object> map = new HashMap<>();
        map.put("secureLevel",defaultLevel);
        map.put("businessCode",bizCode);
        return captchaRuleDao.queryCaptchaRulesCount(map)==1;
    }
}
