package com.upao.panaderia.controllers

import android.content.Context
import com.upao.panaderia.models.requestModel.PedidosRequest
import com.upao.panaderia.service.PedidoService

class PedidoController(context: Context) {

    private val pedidoService = PedidoService(context)

    fun createPedido(context: Context, pedido: PedidosRequest, onResult: (String) -> Unit) {
        pedidoService.createPedido(context, pedido) { idPedido ->
            onResult(idPedido)
        }
    }

    fun getPedido(context: Context, id: Int, onResult: (String) -> Unit) {
        pedidoService.getPedido(context, id) { idPedido ->
            onResult(idPedido)
        }
    }
}