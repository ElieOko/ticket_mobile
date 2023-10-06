package com.odc.ticket.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BranchModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "branchId") val branchId: Int,
    @ColumnInfo(name = "branchName") val branchName: String,
    @ColumnInfo(name = "branchZone") val branchZone: String,
)