package com.lvmama.captcha.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lvmama.captcha.dto.CaptchaRuleDTO;
import com.lvmama.captcha.dto.FontDTO;
import com.lvmama.captcha.dto.NoiseDTO;
import com.lvmama.captcha.model.CaptchaRule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianjiangtao on 2016/11/29.
 * 验证码工具类
 */
public class CaptchaUtil {
    private static final Log logger = LogFactory.getLog(CaptchaUtil.class);
    private static final String STR = "000000";

    /**
     * 格式化提供front验证码规则数据
     *
     * @param captchaRule
     * @return
     */
    public static CaptchaRuleDTO formatCaptchaRuleDTO(CaptchaRule captchaRule) {
        CaptchaRuleDTO captchaRuleDTO = null;
        try {
            if (captchaRule!=null) {
                captchaRuleDTO = new CaptchaRuleDTO();
                BeanUtils.copyProperties(captchaRule, captchaRuleDTO);
                //字符长度
                List<Integer> charLength = JSON.parseArray(captchaRule.getCharLength(), Integer.class);
                captchaRuleDTO.setChineseCharLength(charLength.get(0));
                captchaRuleDTO.setEnglishCharLength(charLength.get(1));
                captchaRuleDTO.setNumbersCharLength(charLength.get(2));
                //字体
                captchaRuleDTO.setFonts(JSON.parseArray(captchaRule.getFont(), FontDTO.class));
                //背景渐变色
                List<Integer> bgColor = JSON.parseArray(captchaRule.getBgColor(), Integer.class);
                captchaRuleDTO.setBgPreColor(bgColor.get(0));
                captchaRuleDTO.setBgBackColor(bgColor.get(1));
                //字符颜色
                captchaRuleDTO.setCharColors(JSON.parseArray(captchaRule.getCharColor(), Integer.class));
                //干扰线
                captchaRuleDTO.setNoises(JSON.parseArray(captchaRule.getNoise() , NoiseDTO.class));
                //背景类型
                JSONObject json = (JSONObject) JSON.parse(captchaRule.getBgType());
                captchaRuleDTO.setShadow((Integer) json.get("shadow"));
                captchaRuleDTO.setStretch((Integer) json.get("stretch"));
                captchaRuleDTO.setFishEye((Integer) json.get("fishEye"));
                captchaRuleDTO.setRipple((Integer) json.get("ripple"));
            }
        } catch (Exception e) {
            logger.error("captchaRule转captchaRuleDTO报错",e);
        }
        return captchaRuleDTO;
    }

    /**
     * 将整形的颜色转化为  #......十六进制格式
     *
     * @param inColors
     * @return
     */
    public static List<String> toHexColorList(List<Integer> inColors) {
        List<String> colorList = new ArrayList<String>();
        for (Integer intColor : inColors) {
            colorList.add(toHexColorStr(intColor));
        }
        return colorList;
    }

    public static String toHexColorStr(int intColor) {
        String hexStr = Integer.toHexString(intColor);
        hexStr = STR.substring(0, STR.length() - hexStr.length()) + hexStr;
        return hexStr;
    }
}
