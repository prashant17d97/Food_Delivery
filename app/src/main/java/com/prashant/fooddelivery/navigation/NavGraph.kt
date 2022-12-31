package com.prashant.fooddelivery.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prashant.fooddelivery.ui.*
import com.prashant.fooddelivery.ui.forgot.ForgotPassword
import com.prashant.fooddelivery.ui.login.Login
import com.prashant.fooddelivery.ui.otp.OTP
import com.prashant.fooddelivery.ui.registration.Registration


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screens.Login.route
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
        composable(route = Screens.Registration.route) {
            Registration(navController)
        }
        composable(route = Screens.ForgotPassword.route) {
            ForgotPassword(navController)
        }
        composable(route = Screens.OTP.route) {
            OTP(navController)
        }
    }
}