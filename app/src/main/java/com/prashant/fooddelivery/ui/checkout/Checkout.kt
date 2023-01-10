package com.prashant.fooddelivery.ui.checkout

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.checkout
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements

@SuppressLint("UnrememberedMutableState")
@Composable
fun Checkout(navController: NavController) {
    var isSwitchOn by rememberSaveable {
        mutableStateOf(true)
    }
    var openDialog by remember {
        mutableStateOf(false) // Initially dialog is closed
    }
    val list = remember {
        mutableStateListOf("a", "a", "a", "a", "a", "a")
    }

    if (list.isNotEmpty()) {
        with(uiElements) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                repeat(list.size) {
                    CheckoutItemCard {
                        list.removeAt(it)
                    }
                }

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
                        openDialog = !openDialog
                    }

                }
                if (openDialog) {
                    PopUp(
                        dismiss = { /*openDialog = false*/ },
                        openDialog = true,
                        onClick = {
                            openDialog = it
                            navController.navigate(Screens.Tracking.going(checkout)) {
                                popUpTo(Screens.Checkout.route) {
                                    inclusive = true
                                }
                            }
                        })
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
                        modifier = Modifier
                            .size(20.dp)
                            .weight(0.05f),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                    )
                    SpacerWidth(value = 10.dp)
                    Text(
                        modifier = Modifier
                            .weight(0.6f)
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
                    CustomSwitch(
                        checked = isSwitchOn,
                        onCheckedChange = { isSwitchOn = !isSwitchOn },
                        inActiveColor = colorResource(id = R.color.inActiveColor),
                        activeColor = MaterialTheme.colors.primaryVariant
                    )
                }
            }


        }
    } else {
        AnimatedVisibility(visible = list.isEmpty()) {
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
                uiElements.SpacerHeight(value = 20.dp)
                Text(
                    text = "Your cart is empty",
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    fontSize = 20.sp
                )
                uiElements.SpacerHeight(value = 10.dp)
                Text(
                    text = "Add something in your cart.",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Light,
                        /*textDecoration = TextDecoration.Underline*/
                    ),
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.Home.route) {
                            popUpTo(Screens.Checkout.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }


}

@Composable
fun PopUp(dismiss: () -> Unit, openDialog: Boolean, onClick: (Boolean) -> Unit) {
    Dialog(
        onDismissRequest = { dismiss() },
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .background(
                    color = colorResource(id = R.color.card_bg),
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 40.dp)
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                with(uiElements) {
                    Image(
                        painter = painterResource(id = R.drawable.check_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariant)
                    )
                    SpacerHeight(value = 20.dp)
                    Text(
                        text = "Your order is successfully completed",
                        style = MaterialTheme.typography.body1.copy(
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    SpacerHeight(value = 20.dp)
                    Text(
                        text = "We are already preparing for you the best dishes of our chef and soon you will bring them! ☺",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center
                        ),
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    SpacerHeight(value = 20.dp)
                    OutlinedButton(
                        onClick = { onClick(!openDialog) },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
                        contentPadding = PaddingValues(0.dp),  //avoid the little icon
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.card_bg))
                    ) {

                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = "Ok, go it!", style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )


                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun CheckoutPreView() = Checkout(navController = rememberNavController())/*PopUp(dismiss = {},
    openDialog = false,
    onClick = {})*/


