package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerModel(
    @PrimaryKey(autoGenerate = true) val customerID: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "fatherName") val fatherName: String,
    @ColumnInfo(name = "motherName") val motherName: String,
    @ColumnInfo(name = "phoneNumber1") val phoneNumber1: String,
    @ColumnInfo(name = "phoneNumber2") val phoneNumber2: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "whatsappNumber") val whatsappNumber: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "township") val township: String,
    @ColumnInfo(name = "idCardType1") val idCardType1: Long,
    @ColumnInfo(name = "idCardNumber1") val idCardNumber1: String,
    @ColumnInfo(name = "idCardExpiryDate1") val idCardExpiryDate1: String,
    @ColumnInfo(name = "signature") val signature: String,
)
