package com.upao.panaderia.repositories

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.upao.panaderia.api.apiClient.Apiclient
import com.upao.panaderia.api.apiEndpoints.ApiService
import com.upao.panaderia.models.ApiError
import com.upao.panaderia.models.requestModel.PedidosRequest
import com.upao.panaderia.models.responseModel.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class PedidoRepository(context: Context) {

    suspend fun createPedido(context: Context, pedido: PedidosRequest): String {
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.placeOrder(pedido)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                val registerResponse = response.body()
                val idPedido = registerResponse?.msg
                Toast.makeText(context, "Pedido Creado con exito!", Toast.LENGTH_SHORT).show()
                idPedido ?: ""
            } else {
                val errorResponse = response.errorBody()?.string()
                val apiErrors = parseError(errorResponse)
                apiErrors?.let { errors ->
                    for (error in errors) {
                        val capitalizedCode = error.code.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                        Toast.makeText(
                            context,
                            "${capitalizedCode}: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } ?: run {
                    Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
                ""
            }
        }
    }

    private fun parseError(errorBody: String?): List<ApiError>? {
        return try {
            errorBody?.let {
                val gson = Gson()
                val errorResponse = gson.fromJson(it, ErrorResponse::class.java)
                errorResponse.errors
            }
        } catch (e: Exception) {
            null
        }
    }
}