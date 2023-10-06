package com.odc.ticket.data.models;

import androidx.annotation.NonNull;

@SuppressWarnings({"unused","RedundantSuppression"})
public class RatingModel {

    private String title;

    private String code;

    public RatingModel(){

    }

    public RatingModel(String title,String code){
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
