package com.sevban.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevban.database.entity.DimensionsEntity
import com.sevban.database.entity.MetaEntity
import com.sevban.database.entity.ProductEntity
import com.sevban.database.entity.ReviewEntity

@Database(
    entities = [ProductEntity::class, MetaEntity::class, DimensionsEntity::class, ReviewEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}