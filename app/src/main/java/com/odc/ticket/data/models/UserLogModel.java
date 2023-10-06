package com.odc.ticket.data.models;

import com.google.gson.annotations.SerializedName;

public class UserLogModel {

    @SerializedName("userLogId")
    private Long id;

    @SerializedName("userId ")
    private Long userId;

    @SerializedName("clientType")
    private String clientType;

    @SerializedName("clientIpAddress")
    private String clientIp;

    @SerializedName("accessType")
    private String accessType;

    @SerializedName("logTime")
    private String logTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }
}
