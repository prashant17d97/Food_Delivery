package com.prashant.fooddelivery.ui.restaurant

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.methods.CommonMethod.fromJson
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.restaurantPageArgs
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun RestaurantsPage(navController: NavController) {
    val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sodales" +
            "laoreet commodo. Phasellus a purus eu risus elementum consequat. Aenean eu" +
            "elit ut nunc convallis laoreet non ut libero. Suspendisse interdum placerat" +
            "risus vel ornare. Donec vehicula, turpis sed consectetur ullamcorper, ante" +
            "nunc egestas quam, ultricies adipiscing velit enim at nunc. Aenean id diam" +
            "neque. Praesent ut lacus sed justo viverra fermentum et ut sem. Fusce" +
            "convallis gravida lacinia. Integer semper dolor ut elit sagittis lacinia." +
            "Praesent sodales scelerisque eros at rhoncus. Duis posuere sapien vel ipsum" +
            "ornare interdum at eu quam. Vestibulum vel massa erat. Aenean quis sagittis" +
            "purus. Phasellus arcu purus, rutrum id consectetur non, bibendum at nibh."
    var showMore by rememberSaveable() {
        mutableStateOf(false)
    }
    val restaurants = listOf(
        RestaurantDishModel(
            restaurantName = "Wavos rancheros",
            restaurantOffers = "",
            stars = 4.0f,
            comments = 312,
            sales = 412,
            icon = R.drawable.img_wavos,
            isRestaurant = false
        ),
        RestaurantDishModel(
            restaurantName = "Enchiladas",
            restaurantOffers = "",
            stars = 4.0f,
            comments = 312,
            sales = 412,
            icon = R.drawable.img_ench,
            isRestaurant = false
        ),
        RestaurantDishModel(
            restaurantName = "Pico de gallo",
            restaurantOffers = "",
            stars = 4.0f,
            comments = 312,
            sales = 412,
            icon = R.drawable.img_pico_de,
            isRestaurant = false
        )
    )
    val menus = listOf(
        /*RestaurantDishModel(
            restaurantName = "Wavos rancheros",
            restaurantOffers = "",
            stars = 4.0f,
            comments = 312,
            sales = 412,
            icon = R.drawable.img_wavos,
            isRestaurant = false
        ),*/
        RestaurantDishModel(
            restaurantName = "Enchiladas",
            restaurantOffers = "",
            stars = 4.0f,
            comments = 312,
            sales = 412,
            icon = R.drawable.img_ench,
            isRestaurant = false
        ),
        RestaurantDishModel(
            restaurantName = "Pico de gallo",
            restaurantOffers = "",
            stars = 4.0f,
            comments = 312,
            sales = 412,
            icon = R.drawable.img_pico_de,
            isRestaurant = false
        )
    )
    val restaurantDetail =
        navController.currentBackStackEntry?.arguments?.getString(restaurantPageArgs)?.let {
            fromJson<RestaurantDishModel>(
                data = it
            )
        }
    Log.e("TAG", "RestaurantsPage: $restaurantDetail")
    var isFavourite by rememberSaveable {
        mutableStateOf(false)
    }
    val icon =
        if (isFavourite) painterResource(R.drawable.like_icon_fill) else painterResource(R.drawable.like_icon)
    with(UIElements()) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "",
                    modifier = Modifier.clickable(
                        onClick = { navController.popBackStack() }
                    )
                )
                Icon(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { isFavourite = !isFavourite }),
                    painter = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primaryVariant
                )

            }
            Spacer(modifier = Modifier.height(20.dp))

            if (restaurantDetail != null) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.card_bg),
                            shape = MaterialTheme.shapes.medium
                        )
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = restaurantDetail.icon),
                        contentDescription = "Restaurant Logo",
                        modifier = Modifier.size(70.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = restaurantDetail.restaurantName,
                            style = MaterialTheme.typography.body1.copy(
                                color = colorResource(id = R.color.text_color)
                            )
                        )
                        Text(
                            text = restaurantDetail.restaurantOffers,
                            style = MaterialTheme.typography.body2.copy(
                                color = colorResource(id = R.color.text_color),
                                fontSize = 10.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Column(modifier = Modifier
                            .animateContentSize(animationSpec = tween(100))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { showMore = !showMore }) {

                            // if showMore is true, the Text will expand
                            // Else Text will be restricted to 3 Lines of display
                            if (showMore) {
                                Text(
                                    text = loremIpsum,
                                    style = MaterialTheme.typography.body2.copy(
                                        colorResource(id = R.color.text_color), fontSize = 10.sp
                                    ),
                                    textAlign = TextAlign.Justify
                                )
                            } else {
                                Text(
                                    text = loremIpsum,
                                    style = MaterialTheme.typography.body2.copy(
                                        colorResource(id = R.color.text_color), fontSize = 10.sp
                                    ),
                                    textAlign = TextAlign.Justify,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(count = restaurants.size, itemContent = { index ->
                    RestaurantDishCard(restaurants[index], isBottomRowRequire = true, onCardClick = {
                        navController.navigate(Screens.ItemPage.route)
                    })
                })
            }

            Text(
                text = stringResource(id = R.string.all_menus),
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
                    items(count = menus.size, itemContent = { item ->
                        RestaurantDishCard(menus[item], isBottomRowRequire = true,isSpan=true, onCardClick = {
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
                    items(count = menus.size, itemContent = { item ->
                        RestaurantDishCard(menus[item], isBottomRowRequire = true,isSpan=true, onCardClick = {
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
                    items(count = menus.size, itemContent = { item ->
                        RestaurantDishCard(menus[item], isBottomRowRequire = true,isSpan=true, onCardClick = {
                            navController.navigate(Screens.ItemPage.route)
                        })
                    })
                }
            )
        }
    }


}

@Preview
@Composable
fun RestaurantsPagePreview() = RestaurantsPage(rememberNavController())