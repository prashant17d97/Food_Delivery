package com.prashant.fooddelivery.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prashant.fooddelivery.ui.Account
import com.prashant.fooddelivery.ui.Checkout
import com.prashant.fooddelivery.ui.Home
import com.prashant.fooddelivery.ui.Tracking
import com.prashant.fooddelivery.ui.login.Login


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            Home(navController)
        }
        composable(route = Screens.Tracking.route) {
            Tracking(navController)
        }
        composable(route = Screens.Checkout.route) {
            Checkout(navController)
        }
        composable(route = Screens.Account.route) {
            Account(navController)
        }
        composable(route = Screens.Login.route) {
            Login(navController)
        }
    }
}