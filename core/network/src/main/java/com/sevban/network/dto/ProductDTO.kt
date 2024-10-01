package com.sevban.network.dto

data class ProductDTO(
    val id: Int,
    val title: String,
    val description: String,
    val meta: MetaDTO,
    val brand: String?,
    val price: Double,
    val discountPercentage: Double,
    val category: String,
    val availabilityStatus: String,
    val dimensions: DimensionsDTO,
    val images: List<String>,
    val tags: List<String>,
    val reviews: List<ReviewDTO>,
    val minimumOrderQuantity: Int,
    val rating: Double,
    val returnPolicy: String,
    val shippingInformation: String,
    val sku: String,
    val stock: Int,
    val thumbnail: String,
    val warrantyInformation: String,
    val weight: Int
)