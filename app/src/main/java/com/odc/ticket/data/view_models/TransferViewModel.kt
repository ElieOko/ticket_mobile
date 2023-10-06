package com.odc.ticket.data.view_models

import com.google.gson.annotations.SerializedName
import com.odc.ticket.data.models.room.BranchModel
//import com.soficom_transfert.tickets.data.models.BranchModel
import java.io.InputStream

data class TransferViewModel(
    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("amount")
    var amount : Double? = null,

    @SerializedName("senderName")
    var senderName: String? = null,

    @SerializedName("senderPhone")
    var senderPhone: String? = null,

    @SerializedName("receiverName")
    var receiverName: String? = null,

    @SerializedName("receiverPhone")
    var receiverPhone: String? = null,

    @SerializedName("note")
    var note: String? = null,

    @SerializedName("branch")
    var branch: BranchModel? = null,

    @SerializedName("transferTypeId")
    var transferTypeId: Long? = null,

    @SerializedName("currencyId")
    var currencyId: Long? = null,

    @SerializedName("address")
    var location: String? = null,

    @SerializedName("intervalId")
    var intervalId: Long? = null,

    @SerializedName("signature")
    var signature: String? = null,

    @SerializedName("cardId")
    var cardId: Long? = null,

    @SerializedName("cardPath")
    var cardPath: String? = null,

    @SerializedName("cardExpiryDate")
    var cardExpiryDate: String? = null,

    @SerializedName("uniqueString")
    var uniqueString: String? = null,

    @Transient
    var fileStream: InputStream? = null,

    @SerializedName("Ticket")
    var token: String? = null,

    @SerializedName("Code")
    var code: String? = null,
)