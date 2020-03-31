package com.swpu.system_manager.domain;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Role implements Serializable {
    private String id;
    private String roleName;
    private String description;
    private String state; //-1表示禁用，1表示启用
    private String createTime;
    private String updateTime;
    private Boolean LAY_CHECKED;

    public Role() {
    }
    @JsonProperty("LAY_CHECKED")
    public Boolean getLAY_CHECKED() {
        return LAY_CHECKED;
    }

    public void setLAY_CHECKED(Boolean LAY_CHECKED) {
        this.LAY_CHECKED = LAY_CHECKED;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
