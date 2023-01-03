package com.prashant.fooddelivery.navigation

import com.prashant.fooddelivery.R

/**
 * Arguments Ids*/
const val comingFrom = "ComingFrom"
const val RestPassword = "ForgotPassword"
const val RegistrationScreen = "Registration"


/**
 *
 * Screen Ids
 * */


/**
 * OnBoardings*/
const val login = "Login"
const val registration = "Registration"
const val forgotPassword = "ForgotPassword"
const val otp = "OTP"
const val resetPassword = "ResetPassword"


const val home = "Home"
const val tracking = "Tracking"
const val checkout = "Checkout"
const val account = "Account"
const val search = "Search"

sealed class Screens(var title: String, var icon: Int = 0, var route: String) {

    /**Bottom navigation control*/
    object Home : Screens(home, R.drawable.home_icon, route = home) /*{
        fun homeArgs(demo: String): String {
            return "$home/$demo"
        }
    }*/
    object Tracking : Screens(tracking, R.drawable.map_icon, route = tracking)
    object Checkout : Screens(checkout, R.drawable.checkout_icon, route = checkout)
    object Account : Screens(account, R.drawable.user_icon, route = account)
    object Search : Screens(search, route = search)

    /** OnBoarding screen Controls*/
    object Login : Screens(title = login, route = login)
    object Registration : Screens(title = registration, route = registration)
    object ForgotPassword : Screens(title = forgotPassword, route = forgotPassword)
    object OTP : Screens(title = otp, route = "$otp/{$comingFrom}"){
        fun otpArgs(comingFrom: String): String {
            return "$otp/$comingFrom"
        }
    }
    object ResetPassword :Screens(title = resetPassword, route = resetPassword)
}