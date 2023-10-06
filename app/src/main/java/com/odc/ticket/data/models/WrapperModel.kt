package com.odc.ticket.data.models

import com.google.gson.annotations.SerializedName
import com.odc.ticket.data.models.room.BranchModel
import com.odc.ticket.data.models.room.CardModel
import com.odc.ticket.data.models.room.CurrencyModel
import com.odc.ticket.data.models.room.IntervalModel
import com.odc.ticket.data.models.room.TitleModel
import com.odc.ticket.data.models.room.TransferTypeModel

data class WrapperModel (
    @SerializedName("branches")
    var branchModels: List<BranchModel>? = null,

    @SerializedName("cards")
    var cardModels: List<CardModel>? = null,

    @SerializedName("currencies")
    var currencyModels: List<CurrencyModel>? = null,

    @SerializedName("intervals")
    var intervalModels: List<IntervalModel>? = null,

    @SerializedName("titles")
    var titleModels: List<TitleModel>? = null,

    @SerializedName("transferTypes")
    var transferTypeModels: List<TransferTypeModel>? = null,

    @SerializedName("userLogs")
    var userLogModels: List<UserLogModel>? = null,
)