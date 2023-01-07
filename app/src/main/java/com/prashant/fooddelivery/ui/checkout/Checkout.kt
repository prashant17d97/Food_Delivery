package com.prashant.fooddelivery.ui.checkout

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
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
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.uielement.UIElements

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Checkout(navController: NavController) {
    var isSwitchOn by rememberSaveable {
        mutableStateOf(true)
    }

    val list = mutableStateListOf("a", "a", "a", "a"/*,"a","a","a","a","a",*/)

    with(UIElements()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            LazyColumn(userScrollEnabled = false, content = {
                itemsIndexed(list) { index, item ->
                    CheckoutItemCard {
                        list.removeAt(index)
                    }

                }
            })
            SpacerHeight(value = 20.dp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.time_icon),
                            contentDescription = "Sale",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "  40 m.", style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ), fontSize = 12.sp
                            )
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.wallet_icon),
                            contentDescription = "Sale",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "  $1,575", style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.price_color
                                ), fontSize = 12.sp
                            )
                        )
                    }
                }
                SpacerHeight(value = 40.dp)
                GradientButtonNoRipple(textOnButton = stringResource(id = R.string.checkout)) {

                }
            }

            SpacerHeight(value = 20.dp)
            Text(
                text = "Order details", style = MaterialTheme.typography.body2.copy(
                    color = colorResource(
                        id = R.color.text_color
                    )
                )
            )
            SpacerHeight(value = 10.dp)
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.phone_icon),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                )
                SpacerWidth(value = 10.dp)
                Text(
                    text = "+91 1234567890", style = MaterialTheme.typography.body2.copy(
                        color = colorResource(
                            id = R.color.text_color
                        ), fontSize = 12.sp
                    )
                )
            }
            SpacerHeight(value = 10.dp)
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.place_icon),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                )
                SpacerWidth(value = 10.dp)
                Text(
                    text = "USA, Oklahoma, Springdale, 4301-4499 NW …",
                    style = MaterialTheme.typography.body2.copy(
                        color = colorResource(
                            id = R.color.text_color
                        ), fontSize = 12.sp
                    )
                )
            }
            SpacerHeight(value = 10.dp)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.place_icon),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                )
                SpacerWidth(value = 10.dp)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    text = "Auto detection of geolocation",
                    style = MaterialTheme.typography.body2.copy(
                        color = colorResource(
                            id = R.color.text_color
                        ), fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Start
                )
                SpacerWidth(value = 10.dp)
                Switch(
                    checked = isSwitchOn,
                    onCheckedChange = { isSwitchOn = !isSwitchOn },
                    modifier = Modifier.padding(end = 10.dp)
                )
            }

        }
    }

    /*AnimatedVisibility(visible = list.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.tray_icon),
                modifier = Modifier.size(120.dp),
                contentDescription = ""
            )
            UIElements().SpacerHeight(value = 20.dp)
            Text(
                text = "Your cart is empty",
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                fontSize = 20.sp
            )
        }
    }*/


}

@Preview
@Composable
fun CheckoutPreView() = Checkout(navController = rememberNavController())