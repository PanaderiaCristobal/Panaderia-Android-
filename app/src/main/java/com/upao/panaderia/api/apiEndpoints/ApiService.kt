package com.upao.panaderia.api.apiEndpoints

import com.upao.panaderia.models.requestModel.RegisterRequest
import com.upao.panaderia.models.responseModel.ApiResponse
import com.upao.panaderia.models.responseModel.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("user/store")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<ApiResponse>

    @POST("vendor/store")
    suspend fun registerVendor(@Body registerRequest: RegisterRequest): Response<ApiResponse>
}