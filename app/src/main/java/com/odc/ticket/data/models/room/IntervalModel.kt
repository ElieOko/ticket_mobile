package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class IntervalModel (
    @PrimaryKey(autoGenerate = true) val intervalId: Int,
    @ColumnInfo(name = "TransfertTypeId") val TransfertTypeId: Int,
    @ColumnInfo(name = "CurrencyId") val CurrencyId: Int,
    @ColumnInfo(name = "min") val min: Int,
    @ColumnInfo(name = "max") val max: Int,
)


