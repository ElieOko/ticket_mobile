package com.odc.ticket.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.odc.ticket.data.models.room.BranchModel

data class UserModel(
    @SerializedName("userId")
        var id: Long = 0,

    @SerializedName("username")
        var userName: String? = null,

    @SerializedName("password")
        var password: String? = null,

    @SerializedName("branch")
        var branch: BranchModel? = null,

    @SerializedName("userType")
        var userType: String? = null,

    @SerializedName("accessToken")
        var tokenString: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(BranchModel::class.java.classLoader) as BranchModel,
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(userName)
        parcel.writeString(password)
        parcel.writeParcelable(branch as Parcelable, flags)
        parcel.writeString(userType)
        parcel.writeString(tokenString)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }

}