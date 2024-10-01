package com.sevban.data.repository

import com.sevban.model.Product

interface ProductsOfflineFirstRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(id: Int): Product
}