package com.swpu.apply_server.domain;

import java.io.Serializable;

public class Apply implements Serializable {
    private String id;
    private String applyName;//应用名称
    private String icon;//图标
    private String applySystem;//应用系统
    private String description;//描述
    private String state;//审核状态：1：审核通过；0：审核中；-1：审核未通过;2:已经发布；-2:下架；
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getApplySystem() {
        return applySystem;
    }

    public void setApplySystem(String applySystem) {
        this.applySystem = applySystem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id='" + id + '\'' +
                ", applyName='" + applyName + '\'' +
                ", icon='" + icon + '\'' +
                ", applySystem='" + applySystem + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
