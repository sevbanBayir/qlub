package com.sevban.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sevban.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productEntities: List<ProductEntity>)

    // Query Operations
    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId: Int): ProductEntity

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductEntity>>
}