package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TitleModel(
    @PrimaryKey(autoGenerate = true) val titleId: Int,
    @ColumnInfo(name = "titleName") val titleName: String,
    @ColumnInfo(name = "displayName") val displayName: String,
)

