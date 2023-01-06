package com.prashant.fooddelivery.ui

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun ItemPage(navController: NavController) {
    var isFavourite by rememberSaveable {
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
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_wavos_logo),
                    contentDescription = "",
                    modifier = Modifier.size(200.dp)
                )

                Text(
                    text = "Wavos rencheros", style = MaterialTheme.typography.body1.copy(
                        color = colorResource(
                            id = R.color.text_color
                        ),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "This is a classic homemade salsa made from tomatoes, chili, fresh and smoked. What makes it different from others? Lots of chili. This is a thick, burning hot salsa, cooked in a hot way - heat treated. We choose ripe fleshy tomatoes, chili of different varieties and spiciness for ranchers, and smoked chili for piquancy and smoky aroma (in my photo, black and wrinkled chipotle morita).",
                    style = MaterialTheme.typography.body2.copy(
                        color = colorResource(
                            id = R.color.text_color
                        ),
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_comment),
                            contentDescription = "Comment",
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = " 316",
                            style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ),
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_sale),
                            contentDescription = "Sale",
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = " 570",
                            style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ),
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.time_icon),
                            contentDescription = "Sale"
                        )
                        Text(
                            text = " 40 m.",
                            style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ),
                                fontSize = 12.sp
                            )
                        )
                    }
                    RatingBar(
                        value = 4f,
                        config = RatingBarConfig()
                            .activeColor(MaterialTheme.colors.primaryVariant)
                            .inactiveBorderColor(MaterialTheme.colors.primaryVariant)
                            .numStars(5)
                            .size(14.dp)
                            .style(RatingBarStyle.HighLighted),
                        onValueChange = {

                        },
                        onRatingChanged = {
                            Log.d("TAG", "onRatingChanged: $it")
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {
                    Text(
                        text = "$ 5,25", style = MaterialTheme.typography.body1.copy(
                            color = colorResource(
                                id = R.color.price_color
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        shape = MaterialTheme.shapes.medium,
                        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
                        contentPadding = PaddingValues(0.dp),  //avoid the little icon
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.card_bg))
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = "Add to cart", style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.tray_icon),
                            contentDescription = "Tray",
                            tint = MaterialTheme.colors.primaryVariant,
                        )

                    }

                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.similar_dishes),
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
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.comments),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.body2.copy(
                    color = colorResource(
                        id = R.color.text_color
                    )
                )
            )
            repeat(3) {
                CommentCard()
            }
        }
    }
}

@Preview
@Composable
fun ItemPagePreview() = ItemPage(rememberNavController())