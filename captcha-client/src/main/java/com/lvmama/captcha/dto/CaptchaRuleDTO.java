package com.lvmama.captcha.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qianjiangtao on 2016/11/30.
 * 提供captcha_front验证码规则数据
 */
public class CaptchaRuleDTO implements Serializable {
    private static final long serialVersionUID = -7550043301387011010L;
    //验证码宽度
    private int imgWidth;
    //验证码高度
    private int imgHeight;
    //验证码类型
    private String charType;
    //验证码字符长度
    private int chineseCharLength;
    private int englishCharLength;
    private int numbersCharLength;
    //字体
    private List<FontDTO> fonts;
    //背景渐变颜色
    private int bgPreColor;
    private int bgBackColor;
    //字符颜色
    private List<Integer> charColors;
    //干扰线
    private List<NoiseDTO> noises;
    //粘连百分比
    private int overlapPercent;
    //背景类型
    private int shadow;     //阴影
    private int stretch;    //伸展
    private int fishEye;    //网格
    private int ripple;     //波浪
    //是否旋转
    private String rotate;
    //过期时间
    private int expireTime;

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public String getCharType() {
        return charType;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

    public int getChineseCharLength() {
        return chineseCharLength;
    }

    public void setChineseCharLength(int chineseCharLength) {
        this.chineseCharLength = chineseCharLength;
    }

    public int getEnglishCharLength() {
        return englishCharLength;
    }

    public void setEnglishCharLength(int englishCharLength) {
        this.englishCharLength = englishCharLength;
    }

    public int getNumbersCharLength() {
        return numbersCharLength;
    }

    public void setNumbersCharLength(int numbersCharLength) {
        this.numbersCharLength = numbersCharLength;
    }

    public List<FontDTO> getFonts() {
        return fonts;
    }

    public void setFonts(List<FontDTO> fonts) {
        this.fonts = fonts;
    }

    public int getBgPreColor() {
        return bgPreColor;
    }

    public void setBgPreColor(int bgPreColor) {
        this.bgPreColor = bgPreColor;
    }

    public int getBgBackColor() {
        return bgBackColor;
    }

    public void setBgBackColor(int bgBackColor) {
        this.bgBackColor = bgBackColor;
    }

    public List<Integer> getCharColors() {
        return charColors;
    }

    public void setCharColors(List<Integer> charColors) {
        this.charColors = charColors;
    }

    public List<NoiseDTO> getNoises() {
        return noises;
    }

    public void setNoises(List<NoiseDTO> noises) {
        this.noises = noises;
    }

    public int getOverlapPercent() {
        return overlapPercent;
    }

    public void setOverlapPercent(int overlapPercent) {
        this.overlapPercent = overlapPercent;
    }

    public int getShadow() {
        return shadow;
    }

    public void setShadow(int shadow) {
        this.shadow = shadow;
    }

    public int getStretch() {
        return stretch;
    }

    public void setStretch(int stretch) {
        this.stretch = stretch;
    }

    public int getFishEye() {
        return fishEye;
    }

    public void setFishEye(int fishEye) {
        this.fishEye = fishEye;
    }

    public int getRipple() {
        return ripple;
    }

    public void setRipple(int ripple) {
        this.ripple = ripple;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }
}

