package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransferTypeModel(
    @PrimaryKey(autoGenerate = true) val transferTypeId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "displayName") val displayName: String,
)

