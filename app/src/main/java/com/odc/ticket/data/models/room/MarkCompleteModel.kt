package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class MarkCompleteModel (
    @PrimaryKey(autoGenerate = true) val transferId: Int,
    @ColumnInfo(name = "completeNote") val completeNote: String,
)
