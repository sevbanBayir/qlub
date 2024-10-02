package com.sevban.model

data class Review(
    val rating: Int,
    val reviewerEmail: String,
    val reviewerName: String,
    val comment: String,
    val date: String
)
