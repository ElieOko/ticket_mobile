package com.odc.ticket.data.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings({"unused","RedundantSuppression"})
public class MarkCompleteModel {

    @SerializedName("transferId")
    private long id;

    @SerializedName("completeNote")
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
