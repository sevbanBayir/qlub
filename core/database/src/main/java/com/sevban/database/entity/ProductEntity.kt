package com.sevban.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "products")
@TypeConverters(StringListConverter::class, ReviewTypeConverter::class)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    @Embedded
    val metaEntity: MetaEntity,
    val brand: String?,
    val price: Double,
    val discountPercentage: Double,
    val category: String,
    val availabilityStatus: String,
    @Embedded
    val dimensionsEntity: DimensionsEntity,
    val images: List<String>,
    val tags: List<String>,
    val reviewEntities: List<ReviewEntity>,
    val minimumOrderQuantity: Int,
    val rating: Double,
    val returnPolicy: String,
    val shippingInformation: String,
    val sku: String,
    val stock: Int,
    val thumbnail: String,
    val warrantyInformation: String,
    val weight: Int,
    val timestamp: Long = System.currentTimeMillis()
)

class StringListConverter {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}

class ReviewTypeConverter {

    @TypeConverter
    fun fromReviewList(value: List<ReviewEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ReviewEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toReviewList(value: String): List<ReviewEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<ReviewEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}


