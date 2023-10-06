package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CardModel(
    @PrimaryKey(autoGenerate = true) val cardId: Int,
    @ColumnInfo(name = "cardName") val cardName: String,
)