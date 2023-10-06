package com.odc.ticket.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.odc.ticket.data.models.room.BranchModel

data class TransferModel(
    @SerializedName("id")
        var id: Long? = null,

    @SerializedName("transferTypeId")
        var transferTypeId: Long? = null,

    @SerializedName("amount")
        var amount : Double? = null,

    @SerializedName("currencyId")
        var currencyId: Long? = null,

    @SerializedName("currencyCode")
        var currencyCode: String? = null,

    @SerializedName("senderName")
        var senderName: String? = null,

    @SerializedName("senderPhone")
        var senderPhone: String? = null,

    @SerializedName("receiverName")
        var receiverName: String? = null,

    @SerializedName("receiverPhone")
        var receiverPhone: String? = null,

    @SerializedName("phone")
        private var phone: String? = null,

    @SerializedName("address")
        var location: String? = null,

    @SerializedName("code")
        var code: String? = null,

    @SerializedName("note")
        var note: String? = null,

    @SerializedName("branch")
        var branch: BranchModel? = null,

    @SerializedName("fromBranchName")
        var fromBranchName: String? = null,

    @SerializedName("toBranchName")
        var toBranchName: String? = null,

    @SerializedName("cardId")
        var cardId: Long? = null,

    @SerializedName("orderNumber")
        var orderNumber: Int? = null,

    @SerializedName("barCode")
        var barcode: String? = null,

    @SerializedName("cardExpiryDate")
        var cardExpiryDate: String? = null,

    @SerializedName("formPath")
        var formPath: String? = null,

    @SerializedName("cardPath")
        var cardPath: String? = null,

    @SerializedName("intervalId")
        var intervalId: Long? = null,

    @SerializedName("dateCreated")
        var dateCreated: String? = null,

    @SerializedName("signature")
        var signature: String? = null,

    @SerializedName("uniqueString")
        var uniqueString: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(BranchModel::class.java.classLoader) as BranchModel,
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(transferTypeId)
        parcel.writeValue(amount)
        parcel.writeValue(currencyId)
        parcel.writeString(currencyCode)
        parcel.writeString(senderName)
        parcel.writeString(senderPhone)
        parcel.writeString(receiverName)
        parcel.writeString(receiverPhone)
        parcel.writeString(phone)
        parcel.writeString(location)
        parcel.writeString(code)
        parcel.writeString(note)
        parcel.writeParcelable(branch as Parcelable, flags)
        parcel.writeString(fromBranchName)
        parcel.writeString(toBranchName)
        parcel.writeValue(cardId)
        parcel.writeValue(orderNumber)
        parcel.writeString(barcode)
        parcel.writeString(cardExpiryDate)
        parcel.writeString(formPath)
        parcel.writeString(cardPath)
        parcel.writeValue(intervalId)
        parcel.writeString(dateCreated)
        parcel.writeString(signature)
        parcel.writeString(uniqueString)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TransferModel> {
        override fun createFromParcel(parcel: Parcel): TransferModel {
            return TransferModel(parcel)
        }

        override fun newArray(size: Int): Array<TransferModel?> {
            return arrayOfNulls(size)
        }
    }
}