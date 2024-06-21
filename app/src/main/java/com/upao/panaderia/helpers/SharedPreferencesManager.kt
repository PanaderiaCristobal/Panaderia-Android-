package com.upao.panaderia.helpers

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private const val PREFS_NAME = "app_prefs"
    private const val USER_KEY = "user_key"
    private const val TOKEN = "token"
    private const val CART_PRODUCTS = "cart_products"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setUserData(context: Context, user: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_KEY, user)
        editor.apply()
    }

    fun getUserData(context: Context): String? {
        return getPreferences(context).getString(USER_KEY, null)
    }

    fun removeUserData(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(USER_KEY)
        editor.apply()
    }

    fun setToken(context: Context, token: String) {
        val editor = getPreferences(context).edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getPreferences(context).getString(TOKEN, null)
    }

    fun saveProduct(context: Context, product: String) {
        val editor = getPreferences(context).edit()
        val products = getProducts(context)?.toMutableSet() ?: mutableSetOf()
        products.add(product)
        editor.putStringSet(CART_PRODUCTS, products)
        editor.apply()
    }

    private fun getProducts(context: Context): MutableSet<String>? {
        return getPreferences(context).getStringSet(CART_PRODUCTS, mutableSetOf())
    }

    fun getCartProducts(context: Context): MutableSet<String>? {
        return getProducts(context)
    }

    fun removeCartProducts(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(CART_PRODUCTS)
        editor.apply()
    }
}