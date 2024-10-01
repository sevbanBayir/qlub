package com.sevban.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DimensionsEntity(
    @PrimaryKey(autoGenerate = true) val dimensionsId: Int = 0,
    val depth: Double,
    val height: Double,
    val width: Double
)