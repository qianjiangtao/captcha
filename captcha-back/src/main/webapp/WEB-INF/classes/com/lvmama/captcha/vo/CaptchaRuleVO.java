package com.lvmama.captcha.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by qianjiangtao on 2016/11/25.
 */
public class CaptchaRuleVO implements Serializable{
    private static final long serialVersionUID = -1991536361023110631L;
    private Long id;
    //业务类型
    private String businessCode;
    //安全级别
    private String secureLevel;
    //验证码宽度
    private int imgWidth;
    //验证码高度
    private int imgHeight;
    //验证码类型
    private String charType;
    //验证码字符长度
    private List<Integer> charLengths;
    //字体
    private List<FontVO> fonts;
    //背景渐变颜色
    private List<String> bgColors;
    //字符颜色
    private List<String> charColors;
    //干扰线
    private List<NoiseVO> noises;
    //粘连百分比
    private int overlapPercent;
    //背景类型
    private int shadow;
    private int stretch;
    private int fishEye;
    private int ripple;
    //是否旋转
    private String rotate;
    //过期时间
    private int expireTime;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getSecureLevel() {
        return secureLevel;
    }

    public void setSecureLevel(String secureLevel) {
        this.secureLevel = secureLevel;
    }

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

    public List<Integer> getCharLengths() {
        return charLengths;
    }

    public void setCharLengths(List<Integer> charLengths) {
        this.charLengths = charLengths;
    }

    public List<FontVO> getFonts() {
        return fonts;
    }

    public void setFonts(List<FontVO> fonts) {
        this.fonts = fonts;
    }

    public List<String> getBgColors() {
        return bgColors;
    }

    public void setBgColors(List<String> bgColors) {
        this.bgColors = bgColors;
    }

    public List<String> getCharColors() {
        return charColors;
    }

    public void setCharColors(List<String> charColors) {
        this.charColors = charColors;
    }

    public List<NoiseVO> getNoises() {
        return noises;
    }

    public void setNoises(List<NoiseVO> noises) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
