package com.sevban.data.repository

import com.sevban.common.helper.timerFlow
import com.sevban.data.mapper.toProduct
import com.sevban.data.mapper.toProductEntity
import com.sevban.data.mapper.toProductList
import com.sevban.database.ProductDao
import com.sevban.model.Product
import com.sevban.network.RetrofitService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.milliseconds

class ProductsOfflineFirstRepositoryImpl(
    private val productDao: ProductDao,
    private val apiService: RetrofitService,
) : ProductsOfflineFirstRepository {

    override suspend fun getProducts(): Flow<List<Product>> {
        return productDao.getProducts().map { it.toProductList() }
    }

    override suspend fun getProductById(id: Int): Product {
        return productDao.getProductById(id).toProduct()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAggregatedProducts(): Flow<List<Product>> {
        return timerFlow(CACHE_VALIDITY_TIMESPAN.milliseconds).onEach {
            val remoteProducts = apiService.getProducts().products
            productDao.insertProducts(remoteProducts.map { it.toProductEntity() })
        }.flatMapLatest {
            getProducts()
        }
    }

    companion object {
        private const val CACHE_VALIDITY_TIMESPAN = (60 * 1000L) // 1 minute
    }
}