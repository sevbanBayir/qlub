package com.sevban.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MetaEntity(
    @PrimaryKey(autoGenerate = true) val metaId: Int = 0,
    val barcode: String,
    val createdAt: String,
    val qrCode: String,
    val updatedAt: String
)

