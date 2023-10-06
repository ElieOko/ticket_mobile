package com.odc.ticket.data.models;

import com.google.gson.annotations.SerializedName;

public class TransferStatusModel {

    @SerializedName("transferId")
    private long id;

    @SerializedName("status")
    private long status;

    @SerializedName("note")
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
