package com.prashant.fooddelivery.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.models.CategoriesModel
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun Home(navController: NavController) {
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }
    val categories = listOf(
        CategoriesModel(categoriesName = "Drink", icon = R.drawable.drink_color_icon),
        CategoriesModel(categoriesName = "Burgers", icon = R.drawable.burger_color_icon),
        CategoriesModel(categoriesName = "Sushi", icon = R.drawable.sushi_color_icon),
        CategoriesModel(categoriesName = "Pizza", icon = R.drawable.pizza_color_icon),
        CategoriesModel(categoriesName = "Noodles", icon = R.drawable.noodles_color_icon),
    )

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
        )/*,
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
        )*/
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        with(UIElements()) {
            SearchCard(
                value = searchValue,
                onValueChange = { searchValue = it },
                textStyle = MaterialTheme.typography.body2.copy(color = colorResource(id = R.color.card_text)),
                backGround = colorResource(id = R.color.card_bg),
                leadingIcon = Icons.Outlined.Search,
                placeHolderColor = colorResource(id = R.color.card_text),
                leadingIconColor = colorResource(id = R.color.card_text),
                trailingIcon = ImageVector.vectorResource(id = R.drawable.filter_icon),
                trailingIconRequire = false,
                trailingIconColor = colorResource(id = R.color.card_text),
                placeHolder = stringResource(id = R.string.search_hint),
                onCardClick = {
                    navController.navigate(Screens.Search.route)
                }, enabled = false
            )
        }
        with(UIElements()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.popular_categories),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.body2.copy(
                        color = colorResource(
                            id = R.color.text_color
                        )
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(count = categories.size, itemContent = { item ->
                        CategoryCard(categories[item])
                    })
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.nearby_rest),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.body2.copy(
                        color = colorResource(
                            id = R.color.text_color
                        )
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(count = restaurants.size, itemContent = { index ->
                        RestaurantDishCard(restaurants[index], onCardClick = {
                            navController.navigate(
                                Screens.RestaurantsPage.restaurantPageRequireArgs(
                                    Gson().toJson(restaurants[index])
                                )
                            )
                        }, isBottomRowRequire = false)
                    })
                }

                Text(
                    text = stringResource(id = R.string.best_dishes),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.body2.copy(
                        color = colorResource(
                            id = R.color.text_color
                        )
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = {
                        items(count = dishes.size, itemContent = { item ->
                            RestaurantDishCard(dishes[item], isBottomRowRequire = true,
                                isSpan=true,onCardClick = {
                                navController.navigate(Screens.ItemPage.route)
                            })
                        })
                    }
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = {
                        items(count = dishes.size, itemContent = { item ->
                            RestaurantDishCard(dishes[item], isBottomRowRequire = true,isSpan=true, onCardClick = {
                                navController.navigate(Screens.ItemPage.route)
                            })
                        })
                    }
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = {
                        items(count = dishes.size, itemContent = { item ->
                            RestaurantDishCard(dishes[item], isBottomRowRequire = true,isSpan=true, onCardClick = {
                                navController.navigate(Screens.ItemPage.route)
                            })
                        })
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomePreView() = Home(navController = rememberNavController())