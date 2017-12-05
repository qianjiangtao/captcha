package com.lvmama.captcha.dto;

import java.io.Serializable;

/**
 * Created by qianjiangtao on 2016/12/5.
 */
public class NoiseDTO implements Serializable{
    private static final long serialVersionUID = -6722547820018198967L;

    private int noiseColor;
    private int noiseWidth;

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
}
