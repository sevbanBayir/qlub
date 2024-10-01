package com.sevban.data.mapper

import com.sevban.database.entity.DimensionsEntity
import com.sevban.database.entity.MetaEntity
import com.sevban.database.entity.ProductEntity
import com.sevban.database.entity.ReviewEntity
import com.sevban.network.dto.DimensionsDTO
import com.sevban.network.dto.MetaDTO
import com.sevban.network.dto.ProductDTO
import com.sevban.network.dto.ReviewDTO
import com.sevban.model.Product

fun ProductDTO.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        metaEntity = meta.toMetaEntity(),
        brand = brand,
        price = price,
        discountPercentage = discountPercentage,
        category = category,
        availabilityStatus = availabilityStatus,
        dimensionsEntity = dimensions.toDimensionsEntity(),
        images = images,
        tags = tags,
        reviewEntities = reviews.map { it.toReviewEntity(this.id) },
        minimumOrderQuantity = minimumOrderQuantity,
        rating = rating,
        returnPolicy = returnPolicy,
        shippingInformation = shippingInformation,
        sku = sku,
        stock = stock,
        thumbnail = thumbnail,
        warrantyInformation = warrantyInformation,
        weight = weight
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        createdAt = metaEntity.createdAt,
        brand = brand.orEmpty(),
        price = price,
        discountPercentage = discountPercentage,
        category = category,
        availabilityStatus = availabilityStatus,
        depth = dimensionsEntity.depth,
        height = dimensionsEntity.height,
        width = dimensionsEntity.width,
        images = images,
        tags = tags,
        rating = rating,
        stock = stock
    )
}

fun MetaDTO.toMetaEntity(): MetaEntity {
    return MetaEntity(
        barcode = barcode,
        createdAt = createdAt,
        qrCode = qrCode,
        updatedAt = updatedAt
    )
}

fun DimensionsDTO.toDimensionsEntity(): DimensionsEntity {
    return DimensionsEntity(
        depth = depth,
        height = height,
        width = width
    )
}

fun ReviewDTO.toReviewEntity(productId: Int): ReviewEntity {
    return ReviewEntity(
        rating = rating,
        reviewerEmail = reviewerEmail,
        reviewerName = reviewerName,
        comment = comment,
        date = date,
        productId = productId,
    )
}
