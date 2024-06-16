package com.upao.panaderia

import com.upao.panaderia.models.responseModel.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): List<Category>
}