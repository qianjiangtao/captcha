package com.lvmama.captcha.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lvmama.captcha.constant.CaptchaConstant;
import com.lvmama.captcha.service.BusinessTypeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lvmama.captcha.model.CaptchaRule;
import com.lvmama.captcha.service.CaptchaRuleService;
import com.lvmama.captcha.util.CaptchaUtil;
import com.lvmama.captcha.vo.Page;
import com.lvmama.comm.utils.MemcachedUtil;

/**
 * Created by qianjiangtao on 2016/11/21.
 * 验证码规则
 */
@Controller
@RequestMapping(value = "/captchaRuleController")
public class CaptchaRuleController extends BaseController {

    private static final Log logger = LogFactory.getLog(CaptchaRuleController.class);

    @Resource
    private CaptchaRuleService captchaRuleService;

    @Resource
    private BusinessTypeService businessTypeService;

    @Value("${prefix_url}")
    private String prefixUrl;

    /**
     * 保存验证码规则
     *
     * @param response
     * @param captchaRule
     */
    @RequestMapping(value = "/saveCaptchaRule.do")
    public void saveCaptchaRule(CaptchaRule captchaRule, HttpServletResponse response) {
        String msg = "新建或更新验证码规则失败";
        boolean isSuccess = false;
        try {
            // 新建校验是否有默认级别的验证码(不是默认级别的进行校验)
            String defaultLevel = businessTypeService.queryDefaultLevelByCode(captchaRule.getBusinessCode());
            if (!defaultLevel.equals(captchaRule.getSecureLevel())){
                boolean hasDefaultCaptcha = captchaRuleService.hasDefaultCaptcha(defaultLevel,captchaRule.getBusinessCode());
                if (captchaRule.getId()==null && !hasDefaultCaptcha){
                    returnJsonObject(response,false,"验证码规则必须要有默认级别,请先新创建默认级别的验证码规则");
                    return;
                }
            }
            Long count = captchaRuleService.queryByCodeAndSecureLevel(captchaRule.getBusinessCode(),
                    captchaRule.getSecureLevel());
            // 新建且业务类型和安全级别已经存在则不保存
            if (captchaRule.getId() == null && count > 0) {
                returnJsonObject(response, false, "该业务类型,安全级别的验证码已存在,请删除或修改已存在的!!!");
                return;
            }
            int result = captchaRuleService.saveCaptchaRule(captchaRule);
            if (result == 1) {
                //新建或修改成功将新的验证码规则缓存(30天)
                String chachekey = CaptchaConstant.CAPTCHARULE_PREFIX + captchaRule.getBusinessCode().trim() + "_" + captchaRule.getSecureLevel().trim();
                isSuccess = MemcachedUtil.getInstance().set(chachekey, CaptchaConstant.LONG_CACHE_TIME, CaptchaUtil.formatCaptchaRuleDTO(captchaRule));
                if (isSuccess) {
                    msg = "新建或更新验证码规则成功!!!";
                } else {
                    msg = "缓存验证码规则失败,请重试";
                }
                MemcachedUtil.getInstance().remove(CaptchaConstant.ALL_CODE_LEVELS);
                MemcachedUtil.getInstance().remove(CaptchaConstant.ALL_CODE_DEFAULT_LEVEL);
            }
        } catch (Exception e) {
            msg = "出现异常了,请稍后再试";
            logger.error("新建或更新验证码规则失败,发生异常!!!", e);
        }
        returnJsonObject(response, isSuccess, msg);
    }

    /**
     * 验证码规则列表
     *
     * @param businessCode
     * @param secureLevel
     * @return
     */
    @RequestMapping("/captchaRuleList.do")
    public ModelAndView listCaptchaRules(String businessCode, String secureLevel, Long page, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/WEB-INF/pages/captchaRuleList");
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(businessCode)) {
            params.put("businessCode", businessCode);
        }

        if (StringUtils.isNotBlank(secureLevel)) {
            params.put("secureLevel", secureLevel);
        }

        // 符合条件的验证码规则总数量
        Long captchaRulesCount = captchaRuleService.queryCaptchaRulesCount(params);

        // 封装分页信息
        page = (page == null) ? 1 : page;
        Page pageContent = new Page(captchaRulesCount, PAGE_SIZE, page);
        params.put("startRow", pageContent.getStartRows() - 1);
        params.put("pageSize", PAGE_SIZE);

        List<CaptchaRule> captchaRules = captchaRuleService.queryCaptchaRules(params);
        pageContent.setItems(captchaRules);
        pageContent.buildUrl(request);
        // 业务类型和安全级别联动数据
        mav.addObject("businessTypes", captchaRuleService.queryBusinessAndLevel());

        // 获取验证码预览url前缀
        mav.addObject("prefixURL", prefixUrl);

        mav.addObject("pageContent", pageContent);
        // 查询参数回显
        mav.addObject("businessCode", businessCode);
        mav.addObject("secureLevel", secureLevel);
        return mav;
    }

    /**
     * 跳转到修改和新建的页面
     *
     * @param captchaRuleId
     * @return
     */
    @RequestMapping("/editCaptchaRule.do")
    public ModelAndView editCaptchaRule(Long captchaRuleId) {
        ModelAndView mav = new ModelAndView("/WEB-INF/pages/editCaptcha");
        try {
            // 业务类型和安全级别联动数据
            mav.addObject("businessTypes", captchaRuleService.queryBusinessAndLevel());

            // 修改操作
            if (captchaRuleId != null) {
                mav.addObject("captchaRuleVO", captchaRuleService.queryCaptchaRuleById(captchaRuleId));
            }
        } catch (Exception e) {
            logger.error("获取验证码规则失败,captchaRuleService.queryCaptchaRuleById抛出异常：", e);
        }
        return mav;
    }

    /**
     * 根据id删除验证码规则
     *
     * @param captchaRuleId
     */
    @RequestMapping("/deleteCaptchaRule.do")
    public void deleteCaptchaRule(Long captchaRuleId, String businessCode, String secureLevel, HttpServletResponse response) {
        String msg = "删除失败";
        boolean isSuccess = false;
        try {
            //1.删除缓存
            MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
            String cacheKey = CaptchaConstant.CAPTCHARULE_PREFIX + businessCode.trim() + "_" + secureLevel.trim();
            if (memcachedUtil.get(cacheKey) != null) {
                if (!memcachedUtil.remove(cacheKey)) {
                    returnJsonObject(response, false, "删除缓存失败,请重试！！！");
                    return;
                }
            }
            //2.删除数据
            int result = captchaRuleService.deleteCaptchaRuleById(captchaRuleId);
            if (result == 1) {
                msg = "删除成功";
                isSuccess = true;
                MemcachedUtil.getInstance().remove(CaptchaConstant.ALL_CODE_LEVELS);
                MemcachedUtil.getInstance().remove(CaptchaConstant.ALL_CODE_DEFAULT_LEVEL);
            }
        } catch (Exception e) {
            msg = "出现异常了,清稍后再试";
            logger.error("删除验证码规则失败,captchaRuleId：" + captchaRuleId, e);
        }
        returnJsonObject(response, isSuccess, msg);
    }

    /**
     * 缓存预览数据
     *
     * @param response
     * @param captchaRule
     */
    @RequestMapping(value = "/cacheCaptchaData.do")
    public void catcheCaptchaData(CaptchaRule captchaRule, HttpServletResponse response) {
        String memcacheKey = null;
        String message = "缓存验证码数据失败,请稍后再试!!!";
        try {
            memcacheKey = CaptchaConstant.PREVIEW_PREFIX + captchaRule.getBusinessCode() + "_" + captchaRule.getSecureLevel();
            boolean isSuccess = MemcachedUtil.getInstance().set(memcacheKey, CaptchaConstant.SHORT_CACHE_TIME, CaptchaUtil.formatCaptchaRuleDTO(captchaRule));
            if (!isSuccess) {
                returnJsonObject(response, false, message);
                return;
            }
            returnJsonObject(response, true, "");
        } catch (Exception e) {
            returnJsonObject(response, false, message);
            logger.error("预览缓存验证码数据出错,key:" + memcacheKey, e);
        }
    }
}
