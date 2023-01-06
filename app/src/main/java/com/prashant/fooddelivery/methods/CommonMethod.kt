package com.prashant.fooddelivery.methods

import android.widget.Toast
import com.google.gson.Gson
import com.prashant.fooddelivery.MainActivity

object CommonMethod {
    fun toast(message: String) {
        Toast.makeText(
            MainActivity.context.get(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    inline fun <reified T> fromJson(data: String): T = Gson().fromJson(data, T::class.java)
}