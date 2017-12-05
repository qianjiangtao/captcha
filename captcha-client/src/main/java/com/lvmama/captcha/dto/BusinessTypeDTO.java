package com.lvmama.captcha.dto;

import java.io.Serializable;

/**
 * Created by qianjiangtao on 2016/11/30.
 *
 */
public class BusinessTypeDTO implements Serializable{


    private static final long serialVersionUID = -2242975667242555323L;
    private String code;

    private String defaultLevel;

    private String riskControl;

    private String applicationId; //应用标识

    private String eventId;//事件标识

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultLevel() {
        return defaultLevel;
    }

    public void setDefaultLevel(String defaultLevel) {
        this.defaultLevel = defaultLevel;
    }

    public String getRiskControl() {
        return riskControl;
    }

    public void setRiskControl(String riskControl) {
        this.riskControl = riskControl;
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
