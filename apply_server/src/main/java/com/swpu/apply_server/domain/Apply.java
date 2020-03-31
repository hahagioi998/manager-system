package com.swpu.apply_server.domain;

import java.io.Serializable;

public class Apply implements Serializable {
    private String id;
    private String applyName;//应用名称
    private String icon;//图标
    private String applySystem;//应用系统
    private String description;//描述
    private String auditState;//审核状态：1：审核通过；0：审核中；-1：审核未通过
    private String releaseState;//发布状态：1：发布；-1：未发布
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(String releaseState) {
        this.releaseState = releaseState;
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
                ", auditState='" + auditState + '\'' +
                ", releaseState='" + releaseState + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
