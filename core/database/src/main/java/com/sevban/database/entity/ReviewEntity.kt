package com.sevban.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val reviewId: Int = 0,
    val comment: String,
    val date: String,
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String,
    val productId: Int
)
