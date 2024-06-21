package com.upao.panaderia.controllers

import android.content.Context
import com.upao.panaderia.models.requestModel.RegisterRequest
import com.upao.panaderia.models.requestModel.UserRequest
import com.upao.panaderia.service.UserService

class UserController(context: Context) {

    private val userService = UserService(context)

    // Login method
    fun login(email: String, password: String): Boolean {
        return true
    }

    // Register method
    fun register(context: Context, user: RegisterRequest, onResult: (Boolean) -> Unit) {
        userService.register(context, user) { isSuccess ->
            onResult(isSuccess)
        }
    }

    // Update method
    fun update(user: UserRequest): Boolean {
        return true
    }

    // Delete method
    fun delete(user: UserRequest): Boolean {
        return true
    }
}