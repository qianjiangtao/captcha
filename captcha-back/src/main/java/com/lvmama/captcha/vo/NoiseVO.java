package com.lvmama.captcha.vo;

import com.lvmama.captcha.util.CaptchaUtil;

import java.io.Serializable;

/**
 * Created by qianjiangtao on 2016/11/24.
 */
public class NoiseVO implements Serializable {
    private static final long serialVersionUID = -8659889867159961613L;
    private static final String STR = "000000";
    private int noiseColor;
    private int noiseWidth;
    private String noiseStrColor;

    public NoiseVO() {
    }

    public NoiseVO(int noiseColor, int noiseWidth) {
        this.noiseColor = noiseColor;
        this.noiseWidth = noiseWidth;
    }

    public int getNoiseColor() {
        return noiseColor;
    }

    public void setNoiseColor(int noiseColor) {
        this.noiseColor = noiseColor;
    }

    public int getNoiseWidth() {
        return noiseWidth;
    }

    public void setNoiseWidth(int noiseWidth) {
        this.noiseWidth = noiseWidth;
    }

    public String getNoiseStrColor() {
        return CaptchaUtil.toHexColorStr(getNoiseColor());
    }

    public void setNoiseStrColor(String noiseStrColor) {
        this.noiseStrColor = noiseStrColor;
    }
}
