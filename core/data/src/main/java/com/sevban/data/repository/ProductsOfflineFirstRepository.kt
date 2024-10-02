package com.sevban.data.repository

import com.sevban.model.Product
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

interface ProductsOfflineFirstRepository {
    suspend fun getProducts(): Flow<List<Product>>
    suspend fun getProductById(id: Int): Product
    fun getAggregatedProducts(): Flow<List<Product>>
}