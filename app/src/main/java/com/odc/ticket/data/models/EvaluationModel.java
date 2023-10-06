package com.odc.ticket.data.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings({"unused","RedundantSuppression"})
public class EvaluationModel {

    @SerializedName("evaluationId")
    private long id;

    @SerializedName("transferId")
    private long transferId;

    @SerializedName("rating")
    private String rating;

    @SerializedName("note")
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
