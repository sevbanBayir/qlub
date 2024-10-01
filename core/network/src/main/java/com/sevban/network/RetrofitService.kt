package com.sevban.network

import com.sevban.network.dto.ProductsDTO
import retrofit2.http.GET

interface RetrofitService {
    @GET("products")
    suspend fun getProducts(): ProductsDTO
}