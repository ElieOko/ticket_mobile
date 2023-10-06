package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class EvaluationModel(
    @PrimaryKey(autoGenerate = true) val evaluationId: Int,
    @ColumnInfo(name = "transferId") val transferId: Int,
    @ColumnInfo(name = "currencyName") val currencyName: String,
    @ColumnInfo(name = "rating") val rating: String,
    @ColumnInfo(name = "note") val note: String,
)