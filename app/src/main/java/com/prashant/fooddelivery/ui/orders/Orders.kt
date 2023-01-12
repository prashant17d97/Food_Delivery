package com.prashant.fooddelivery.ui.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.orders
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements

@Composable
fun Orders(navController: NavController) {
    with(uiElements) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            repeat(5) { it ->
                Order(itemList = listOf(
                    RestaurantDishModel(
                        restaurantName = "Homemade Pizza\nPepperoni",
                        restaurantOffers = "",
                        stars = 4.0f,
                        comments = 261,
                        sales = 1367,
                        icon = R.drawable.pepperoni_pizza,
                        isRestaurant = false
                    ),
                    RestaurantDishModel(
                        restaurantName = "Philadelphia rolls\nwith salmon",
                        restaurantOffers = "",
                        stars = 4.0f,
                        comments = 285,
                        sales = 1286,
                        icon = R.drawable.salmon,
                        isRestaurant = false
                    ),
                    RestaurantDishModel(
                        restaurantName = "Philadelphia rolls\n with salmon",
                        restaurantOffers = "",
                        stars = 4.0f,
                        comments = 285,
                        sales = 1286,
                        icon = R.drawable.salmon,
                        isRestaurant = false
                    ),
                    RestaurantDishModel(
                        restaurantName = "Philadelphia rolls\n with salmon",
                        restaurantOffers = "",
                        stars = 4.0f,
                        comments = 285,
                        sales = 1286,
                        icon = R.drawable.salmon,
                        isRestaurant = false
                    )
                ), isOnGoing = it == 0, onClick = {bool->
                    val route = if (bool) {
                        Screens.Tracking.going(orders)
                    } else {
                        Screens.FeedBacks.route
                    }
                    navController.navigate(route)
                }
                )
            }
        }
    }
}

@Preview
@Composable
fun OrdersView() = Orders(navController = rememberNavController())