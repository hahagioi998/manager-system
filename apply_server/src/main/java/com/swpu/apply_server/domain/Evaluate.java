package com.swpu.apply_server.domain;

import java.io.Serializable;

public class Evaluate implements Serializable {
    /**
     * 评价的ID
     */
    private String id;
    /**
     * 应用ID
     */
    private String aId;
    /**
     * 主题
     */
    private String theme;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 评价时间
     */
    private String evaluateTime;
    /**
     * 评价用户姓名
     */
    private String customerName;
    /**
     * 手机型号
     */
    private String phoneModel;
    /**
     * 应用名称
     */
    private String applyName;

    /**
     * 应用系统
     */
    private String applySystem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(String evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplySystem() {
        return applySystem;
    }

    public void setApplySystem(String applySystem) {
        this.applySystem = applySystem;
    }
}
