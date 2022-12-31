package com.prashant.fooddelivery.methods

import android.widget.Toast
import com.prashant.fooddelivery.MainActivity

object CommonMethod {
    fun toast(message: String) {
        val context = MainActivity.context.get()
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}