package com.lvmama.captcha.dto;

import java.io.Serializable;

/**
 * Created by qianjiangtao on 2016/12/2.
 *
 */
public class RiskscoreSecurelevelDTO implements Serializable{

    private static final long serialVersionUID = 207859954678835700L;
    private String businessCode;

    private String secureLevel;

    private Integer fromScore;

    private Integer toScore;

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

    public Integer getFromScore() {
        return fromScore;
    }

    public void setFromScore(Integer fromScore) {
        this.fromScore = fromScore;
    }

    public Integer getToScore() {
        return toScore;
    }

    public void setToScore(Integer toScore) {
        this.toScore = toScore;
    }
}
