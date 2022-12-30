package com.prashant.fooddelivery.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R

@Composable
fun Checkout(navController: NavController) {
    Image(painter = painterResource(id = R.drawable.checkout_icon), contentDescription = "")
}

@Preview
@Composable
fun CheckoutPreView() = Checkout(navController = rememberNavController())