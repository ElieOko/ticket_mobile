package com.odc.ticket.data.view_models

import com.google.gson.annotations.SerializedName

data class CustomerViewModel(
    @SerializedName("phone")
    var phoneNumber: String? = null,
)