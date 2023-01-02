package com.prashant.fooddelivery.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prashant.fooddelivery.ui.Account
import com.prashant.fooddelivery.ui.Checkout
import com.prashant.fooddelivery.ui.home.Home
import com.prashant.fooddelivery.ui.Tracking
import com.prashant.fooddelivery.ui.forgot.ForgotPassword
import com.prashant.fooddelivery.ui.login.Login
import com.prashant.fooddelivery.ui.otp.OTP
import com.prashant.fooddelivery.ui.registration.Registration
import com.prashant.fooddelivery.ui.restpassword.ResetPassword
import com.prashant.fooddelivery.ui.search.Search


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
        composable(route = Screens.OTP.route,
            arguments = listOf(
                navArgument(comingFrom) {
                    type = NavType.StringType
                }
            )) {
            OTP(navController)
        }
        composable(route = Screens.ResetPassword.route) {
            ResetPassword(navController)
        }
        composable(route = Screens.Search.route) {
            Search(navController)
        }
    }
}