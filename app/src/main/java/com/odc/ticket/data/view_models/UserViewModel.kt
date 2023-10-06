package com.odc.ticket.data.view_models

import com.google.gson.annotations.SerializedName

data class UserViewModel(
    @SerializedName("username")
    var name: String? = null,

    @SerializedName("password")
    var pass: String? = null,
)