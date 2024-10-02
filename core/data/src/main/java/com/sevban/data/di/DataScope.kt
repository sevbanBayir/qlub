package com.sevban.data.di

import android.content.Context
import androidx.room.Room
import com.sevban.data.repository.ProductsOfflineFirstRepository
import com.sevban.data.repository.ProductsOfflineFirstRepositoryImpl
import com.sevban.database.ProductDao
import com.sevban.database.ProductsDatabase
import com.sevban.network.RetrofitService
import motif.Expose
import motif.Scope
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Scope
interface DataScope {

    fun productRepository(): ProductsOfflineFirstRepository

    @motif.Objects
    abstract class Objects {

        fun provideProductsDatabase(context: Context): ProductsDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ProductsDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        fun provideProductDao(productsDatabase: ProductsDatabase): ProductDao {
            return productsDatabase.productDao()
        }

        fun provideProductRepository(
            productDao: ProductDao,
            apiService: RetrofitService
        ): ProductsOfflineFirstRepositoryImpl {
            return ProductsOfflineFirstRepositoryImpl(productDao, apiService)
        }

        @Expose
        fun provideRetrofitService(): RetrofitService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RetrofitService::class.java)
        }

        abstract fun provideOfflineFirstRepository(offlineFirstRepositoryImpl: ProductsOfflineFirstRepositoryImpl): ProductsOfflineFirstRepository

    }

    companion object {
        const val BASE_URL = "https://dummyjson.com"
        const val DATABASE_NAME = "products_database"
    }
}
