package com.sevban.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val createdAt: String,
    val brand: String,
    val price: Double,
    val discountPercentage: Double,
    val category: String,
    val availabilityStatus: String,
    val depth: Double,
    val height: Double,
    val width: Double,
    val images: List<String>,
    val tags: List<String>,
    val rating: Double,
    val stock: Int
)