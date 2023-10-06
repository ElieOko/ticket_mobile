package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyModel(
    @PrimaryKey(autoGenerate = true) val currencyId: Int,
    @ColumnInfo(name = "currencyCode") val currencyCode: String,
    @ColumnInfo(name = "currencyName") val currencyName: String,
)
