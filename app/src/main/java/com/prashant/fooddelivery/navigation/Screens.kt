package com.prashant.fooddelivery.navigation

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.prashant.fooddelivery.R

/**
 * Arguments Ids*/
const val comingFrom = "ComingFrom"
const val restPassword = "ForgotPassword"
const val registrationScreen = "Registration"
const val name = "Name"
const val savedDishes = "Saved Dishes"
const val savedRestaurants = "Saved Restaurants"
const val restaurantPageArgs = "RestaurantPageArgs"


/**
 * Screen Ids
 * */
val loremIpsum = LoremIpsum(60).values


/**
 * OnBoardings*/
const val login = "Login"
const val registration = "Registration"
const val forgotPassword = "ForgotPassword"
const val otp = "OTP"
const val resetPassword = "ResetPassword"
const val welcome = "Welcome"


const val home = "Home"
const val tracking = "Tracking"
const val checkout = "Checkout"
const val orders = "Orders"
const val account = "Account"
const val search = "Search"
const val restaurantsPage = "RestaurantsPage"
const val itemPage = "ItemPage"
const val savedDishNRestaurant = "SavedDishNRestaurant"
const val feedBacks = "FeedBacks"
const val transactions = "Transactions"

sealed class Screens(var title: String, var icon: Int = 0, var route: String) {

    /**Bottom navigation control*/
    object Home : Screens(home, R.drawable.home_icon, route = home) /*{
        fun homeArgs(demo: String): String {
            return "$home/$demo"
        }
    }*/
    object Tracking : Screens(tracking, R.drawable.map_icon, route = "$tracking/{$comingFrom}") {
        fun going(comingFrom: String): String {
            return "$tracking/$comingFrom"
        }
    }

    object Checkout : Screens(checkout, R.drawable.checkout_icon, route = checkout)
    object Orders : Screens(orders,  route = orders)
    object Account : Screens(account, R.drawable.user_icon, route = account)
    object Search : Screens(search, route = search)

    /** OnBoarding screen Controls*/
    object Login : Screens(title = login, route = login)
    object Registration : Screens(title = registration, route = registration)
    object ForgotPassword : Screens(title = forgotPassword, route = forgotPassword)
    object OTP : Screens(title = otp, route = "$otp/{$comingFrom}/{$name}") {
        fun requireArguments(comingFrom: String, name: String): String {
            return "$otp/{$comingFrom/$name}"
        }
    }

    object ResetPassword : Screens(title = resetPassword, route = resetPassword)
    object RestaurantsPage :
        Screens(title = restaurantsPage, route = "$restaurantsPage/{$restaurantPageArgs}") {
        fun restaurantPageRequireArgs(restaurantPageArgs: String): String {
            return "$restaurantsPage/$restaurantPageArgs"
        }
    }

    object Welcome : Screens(title = welcome, route = "$welcome/{$name}") {
        fun nameArgs(name: String): String {
            return "$welcome/$name"
        }
    }

    object SavedDishNRestaurant :
        Screens(title = savedDishNRestaurant, route = "$savedDishNRestaurant/{$comingFrom}") {
        fun nameArgs(comingFrom: String): String {
            return "$savedDishNRestaurant/$comingFrom"
        }
    }

    object ItemPage : Screens(title = itemPage, route = itemPage) /*{
        fun nameArgs(name: String): String {
            return "$welcome/$name"
        }
    }*/
    object FeedBacks : Screens(title = feedBacks, route = feedBacks)
    object Transactions : Screens(title = transactions, route = transactions)
}