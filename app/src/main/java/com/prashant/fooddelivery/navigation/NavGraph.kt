package com.prashant.fooddelivery.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.prashant.fooddelivery.ui.account.Account
import com.prashant.fooddelivery.ui.checkout.Checkout
import com.prashant.fooddelivery.ui.forgot.ForgotPassword
import com.prashant.fooddelivery.ui.home.Home
import com.prashant.fooddelivery.ui.items.ItemPage
import com.prashant.fooddelivery.ui.login.Login
import com.prashant.fooddelivery.ui.orders.Orders
import com.prashant.fooddelivery.ui.orders.feedbacks.FeedBacks
import com.prashant.fooddelivery.ui.orders.transactions.Transactions
import com.prashant.fooddelivery.ui.otp.OTP
import com.prashant.fooddelivery.ui.registration.Registration
import com.prashant.fooddelivery.ui.restaurant.RestaurantsPage
import com.prashant.fooddelivery.ui.restpassword.ResetPassword
import com.prashant.fooddelivery.ui.saved.SavedDishNRestaurant
import com.prashant.fooddelivery.ui.search.Search
import com.prashant.fooddelivery.ui.tracking.Tracking
import com.prashant.fooddelivery.ui.welcome.Welcome


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screens.Login.route
    ) {
        composable(route = Screens.Home.route) {
            Home(navController)
        }
        composable(route = Screens.Tracking.route,
            arguments = listOf(
                navArgument(comingFrom) {
                    type = NavType.StringType
                }
            )
        ) {
            Tracking(navController)
        }
        composable(route = Screens.Checkout.route) {
            Checkout(navController)
        }
        composable(route = Screens.Orders.route) {
            Orders(navController)
        }
        composable(route = Screens.FeedBacks.route,arguments = listOf(
            navArgument(feedbackList) {
                type = NavType.StringType
            }
        )
        ) {
            FeedBacks(navController)
        }
        composable(route = Screens.Transactions.route) {
            Transactions(navController)
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
                },
                navArgument(name) {
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

        composable(route = Screens.Welcome.route,
            arguments = listOf(
                navArgument(name) {
                    type = NavType.StringType
                }
            )) {
            Welcome(navController)
        }

        composable(route = Screens.SavedDishNRestaurant.route,
            arguments = listOf(
                navArgument(comingFrom) {
                    type = NavType.StringType
                }
            )) {
            SavedDishNRestaurant(navController)
        }
        composable(
            route = Screens.RestaurantsPage.route,
            arguments = listOf(
                navArgument(restaurantPageArgs) {
                    type = NavType.StringType
                }
            )
        ) {
            RestaurantsPage(navController)
        }
        composable(Screens.ItemPage.route) {
            ItemPage(navController)
        }
    }
}