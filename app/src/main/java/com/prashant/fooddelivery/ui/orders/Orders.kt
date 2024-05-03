package com.prashant.fooddelivery.ui.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.models.OrdersList
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.orders
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements

@Composable
fun Orders(navController: NavController) {
    val list = listOf(
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
    )
    with(uiElements) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color)),
                    contentDescription = "",
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
                Text(
                    text = "My orders",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.body1.copy(
                        color = colorResource(
                            id = R.color.text_color
                        )
                    )
                )

            }
            SpacerHeight(value = 20.dp)
            repeat(5) { it ->
                Order(itemList = list, isOnGoing = it == 0, onClick = { bool ->
                    val route = if (bool) {
                        Screens.Tracking.going(orders)
                    } else {
                        Screens.FeedBacks.list(
                            Gson().toJson(OrdersList(list))
                        )
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

