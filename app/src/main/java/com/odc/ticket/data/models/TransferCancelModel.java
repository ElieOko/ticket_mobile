package com.odc.ticket.data.models;

import com.google.gson.annotations.SerializedName;

public class TransferCancelModel {

    @SerializedName("Ticket")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
