package com.prashant.fooddelivery.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.comingFrom
import com.prashant.fooddelivery.navigation.savedDishes
import com.prashant.fooddelivery.navigation.savedRestaurants
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements

@Composable
fun SavedDishNRestaurant(navController: NavController) {
    val from = navController.currentBackStackEntry?.arguments?.getString(comingFrom) ?: savedRestaurants
    val restaurants = listOf(
        RestaurantDishModel(
            restaurantName = "Market Bistro",
            restaurantOffers = "Fast food restaurant",
            stars = 4.0f,
            comments = 0,
            sales = 0,
            icon = R.drawable.bistro_restaurant_logo,
            isRestaurant = true
        ),
        RestaurantDishModel(
            restaurantName = "Eco house",
            restaurantOffers = "Healthy food",
            stars = 4.0f,
            icon = R.drawable.eco_rest,
            isRestaurant = true
        ),
        RestaurantDishModel(
            restaurantName = "Japanese restaurant",
            restaurantOffers = "Japanese food",
            stars = 4.0f,
            icon = R.drawable.japanes_rest,
            isRestaurant = true
        ),
        RestaurantDishModel(
            restaurantName = "Hugo’s",
            restaurantOffers = "Mexican food",
            stars = 4.0f,
            icon = R.drawable.margarita_logo,
            isRestaurant = true
        )
    )
    val dishes = listOf(
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
                .padding(10.dp)
        ) {
            Text(
                text = from,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.body1.copy(
                    color = colorResource(
                        id = R.color.text_color
                    ),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            )
            SpacerHeight(value = 20.dp)
            AnimatedVisibility(visible = from == savedDishes) {
                VerticalGridCells(
                    list = dishes,
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    RestaurantDishCard(
                        it,
                        isBottomRowRequire = true,
                        isSpan = true,

                        onCardClick = {
                            navController.navigate(Screens.ItemPage.route)
                        },
                        paddingEnd = 0.dp)
                }
            }
            AnimatedVisibility(visible = from == savedRestaurants) {
                VerticalGridCells(
                    list = restaurants,
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    RestaurantDishCard(
                        it,
                        isBottomRowRequire = false,
                        isSpan = true,
                        onCardClick = {
                            navController.navigate(Screens.RestaurantsPage.route)
                        },
                    paddingEnd = 0.dp)
                }
            }
        }
    }
}

@Preview
@Composable
fun SavedDishNRestaurantPreview() = SavedDishNRestaurant(navController = rememberNavController())