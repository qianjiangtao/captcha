package com.lvmama.captcha.vo;

import java.util.Date;

public class RiskscoreSecurelevelVo {
    private Long id;

    private String businessCode;

    private String businessCodeDesc;

    private String secureLevel;

    private Integer fromScore;

    private Integer toScore;

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
        this.businessCode = businessCode == null ? null : businessCode.trim();
    }

    public String getSecureLevel() {
        return secureLevel;
    }

    public void setSecureLevel(String secureLevel) {
        this.secureLevel = secureLevel == null ? null : secureLevel.trim();
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

    public String getBusinessCodeDesc() {
        return businessCodeDesc;
    }

    public void setBusinessCodeDesc(String businessCodeDesc) {
        this.businessCodeDesc = businessCodeDesc;
    }
}