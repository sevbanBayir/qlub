package com.sevban.data.repository

import com.sevban.data.mapper.toProduct
import com.sevban.data.mapper.toProductEntity
import com.sevban.database.ProductDao
import com.sevban.model.Product
import com.sevban.network.RetrofitService

class ProductsOfflineFirstRepositoryImpl(
    private val productDao: ProductDao,
    private val apiService: RetrofitService,
) : ProductsOfflineFirstRepository {

    override suspend fun getProducts(): List<Product> {
        val cachedProducts = productDao.getProducts()
        if (cachedProducts.isNotEmpty() && System.currentTimeMillis() - cachedProducts.first().timestamp < CACHE_VALIDITY_TIMESPAN) {
            return cachedProducts.map { it.toProduct() }
        } else {
            val remoteProducts = apiService.getProducts().products
            productDao.insertProducts(remoteProducts.map { it.toProductEntity() })
            return productDao.getProducts().map { it.toProduct() }
        }
    }

    override suspend fun getProductById(id: Int): Product {
        return productDao.getProductById(id).toProduct()
    }

    companion object {
        private const val CACHE_VALIDITY_TIMESPAN = 60 * 1000 // 1 minute
    }
}