package com.lvmama.captcha.model;

import java.util.Date;

public class BusinessType {
    private Long id;

    private String code;

    private String codeDesc;

    private String canSetLevel;

    private String defaultLevel;

    private String riskControl;

    private String applicationId; //应用标识

    private String eventId;//事件标识

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc == null ? null : codeDesc.trim();
    }

    public String getCanSetLevel() {
        return canSetLevel;
    }

    public void setCanSetLevel(String canSetLevel) {
        this.canSetLevel = canSetLevel == null ? null : canSetLevel.trim();
    }

    public String getDefaultLevel() {
        return defaultLevel;
    }

    public void setDefaultLevel(String defaultLevel) {
        this.defaultLevel = defaultLevel == null ? null : defaultLevel.trim();
    }

    public String getRiskControl() {
        return riskControl;
    }

    public void setRiskControl(String riskControl) {
        this.riskControl = riskControl == null ? "0" : riskControl.trim();
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

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}