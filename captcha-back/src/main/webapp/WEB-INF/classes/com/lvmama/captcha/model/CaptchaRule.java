package com.lvmama.captcha.model;

import java.util.Date;

public class CaptchaRule {
    private Long id;

    private String businessCode;

    private String secureLevel;

    private int imgWidth;

    private int imgHeight;

    private String charType;

    private String charLength;

    private String font;

    private String bgColor;

    private String charColor;

    private String noise;

    private int overlapPercent;

    private String bgType;

    private String rotate;

    private int expireTime;

    private Date createTime;

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

    public String getCharLength() {
        return charLength;
    }

    public void setCharLength(String charLength) {
        this.charLength = charLength;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getCharColor() {
        return charColor;
    }

    public void setCharColor(String charColor) {
        this.charColor = charColor;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    public int getOverlapPercent() {
        return overlapPercent;
    }

    public void setOverlapPercent(int overlapPercent) {
        this.overlapPercent = overlapPercent;
    }

    public String getBgType() {
        return bgType;
    }

    public void setBgType(String bgType) {
        this.bgType = bgType;
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