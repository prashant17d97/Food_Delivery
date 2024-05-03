package com.prashant.fooddelivery.ui.account

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.savedDishes
import com.prashant.fooddelivery.navigation.savedRestaurants
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun Account(navController: NavController) {
    var name by rememberSaveable {
        mutableStateOf("Jenifer")
    }
    var cardCount by rememberSaveable {
        mutableStateOf(1)
    }
    var isEditable by rememberSaveable {
        mutableStateOf(false)
    }
    var isAddressEditable by rememberSaveable {
        mutableStateOf(false)
    }
    var isLocationActive by rememberSaveable {
        mutableStateOf(false)
    }
    var isDarkActive by rememberSaveable {
        mutableStateOf(false)
    }
    var completeAddress by rememberSaveable {
        mutableStateOf("Your Address")
    }
    var addressLine by rememberSaveable {
        mutableStateOf("")
    }
    var district by rememberSaveable {
        mutableStateOf("")
    }
    var state by rememberSaveable {
        mutableStateOf("")
    }
    var pinCode by rememberSaveable {
        mutableStateOf("")
    }
    var country by rememberSaveable {
        mutableStateOf("")
    }
    val editIcon = if (isEditable) R.drawable.ic_setting_edit else R.drawable.ic_setting
    with(UIElements()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(
                        id = R.drawable.ic_comment
                    ),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.Transparent)
                )
                ConstraintLayout {
                    val (image, uploadButton) = createRefs()
                    Image(painter = painterResource(id = R.drawable.user_image),
                        contentDescription = "User Image",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                            })
                    if (isEditable) {
                        Image(painter = painterResource(id = R.drawable.ic_load),
                            contentDescription = "Upload Image",
                            modifier = Modifier.constrainAs(uploadButton) {
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            })
                    }
                }
                Image(painter = painterResource(id = editIcon),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        isEditable = !isEditable
                        isAddressEditable = false
                    })
            }
            SpacerHeight(value = 20.dp)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = true
                ),
                enabled = isEditable,
                textStyle = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center,
                    color= colorResource(id = R.color.text_color)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = if (isEditable) MaterialTheme.colors.primaryVariant else Color.Transparent,
                    unfocusedIndicatorColor = if (isEditable) MaterialTheme.colors.primaryVariant else Color.Transparent,
                    disabledIndicatorColor = if (isEditable) MaterialTheme.colors.primaryVariant else Color.Transparent
                )/*,
                trailingIcon = {
                    if (isEditable){
                        Image(
                            painter = painterResource(id = R.drawable.check_icon),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariant),
                            modifier = Modifier.size(20.dp).clickable {  }
                        )
                    }
                }*/
            )

            AnimatedVisibility(visible = !isEditable) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SquareCard(
                            icon = R.drawable.like_icon, text = "Saved\nDishes"
                        ) {
                            navController.navigate(
                                Screens.SavedDishNRestaurant.nameArgs(
                                    savedDishes
                                )
                            )
                        }
                        SquareCard(
                            icon = R.drawable.restaurants_icon, text = "Saved\nRestaurants"
                        ) {
                            navController.navigate(
                                Screens.SavedDishNRestaurant.nameArgs(
                                    savedRestaurants
                                )
                            )
                        }

                    }
                    SpacerHeight(value = 10.dp)
                    Row(horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable { navController.navigate(Screens.Orders.route) }
                            .fillMaxWidth()
                            .background(
                                color = colorResource(id = R.color.card_bg),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(15.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.tray_icon),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                        )
                        SpacerWidth(value = 10.dp)
                        Text(
                            text = "My Orders", style = MaterialTheme.typography.body2.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ), fontSize = 12.sp
                            )
                        )
                    }
                }


            }
            SpacerHeight(value = 15.dp)
            Text(
                text = stringResource(id = R.string.delivery_address),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body2,
                color= colorResource(id = R.color.text_color)

            )
            SpacerHeight(value = 10.dp)
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(bottom = if (isAddressEditable) 10.dp else 0.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.place_icon),
                        modifier = Modifier.size(20.dp),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                    )
                    SpacerWidth(value = 10.dp)
                    Text(
                        text = completeAddress, style = MaterialTheme.typography.body2.copy(
                            color = colorResource(
                                id = R.color.text_color
                            ), fontSize = 12.sp
                        ), modifier = Modifier.weight(1f)
                    )
                    SpacerWidth(value = 10.dp)
                    if (isEditable) {
                        Image(
                            painter = painterResource(id = if (!isAddressEditable) R.drawable.ic_edit else R.drawable.close_icon),
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { isAddressEditable = !isAddressEditable },
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                        )
                    }
                }
                AnimatedVisibility(visible = isAddressEditable) {
                    val configuration = LocalConfiguration.current
                    val width = (configuration.screenWidthDp - 100) / 2
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        ) {
                            OutlinedTextField(
                                value = addressLine,
                                textStyle = MaterialTheme.typography.body1.copy(
                                    color = colorResource(id = R.color.text_color)
                                ),
                                onValueChange = { addressLine = it },
                                placeholder = {
                                    Text(
                                        text = "Address line",
                                        style = MaterialTheme.typography.body2.copy(
                                            color = colorResource(
                                                id = R.color.card_text
                                            ), fontSize = 12.sp
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                },
                                modifier = Modifier.size(width = width.dp, height = 50.dp)
                            )
                            OutlinedTextField(
                                value = district,
                                textStyle = MaterialTheme.typography.body1.copy(
                                    color = colorResource(id = R.color.text_color)
                                ),
                                onValueChange = { district = it },
                                placeholder = {
                                    Text(
                                        text = "District",
                                        style = MaterialTheme.typography.caption.copy(
                                            color = colorResource(
                                                id = R.color.card_text
                                            ), fontSize = 12.sp
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                },
                                modifier = Modifier.size(width = width.dp, height = 50.dp)
                            )
                        }
                        SpacerHeight(value = 10.dp)
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        ) {
                            OutlinedTextField(
                                value = state,
                                textStyle = MaterialTheme.typography.body1.copy(
                                    color = colorResource(id = R.color.text_color)
                                ),
                                onValueChange = { state = it },
                                placeholder = {
                                    Text(
                                        text = "State", style = MaterialTheme.typography.body2.copy(
                                            color = colorResource(
                                                id = R.color.card_text
                                            ), fontSize = 12.sp
                                        ), modifier = Modifier.weight(1f)
                                    )
                                },
                                modifier = Modifier.size(width = width.dp, height = 50.dp)
                            )
                            OutlinedTextField(
                                value = pinCode,
                                textStyle = MaterialTheme.typography.body1.copy(
                                    color = colorResource(id = R.color.text_color)
                                ),
                                onValueChange = { pinCode = it },
                                placeholder = {
                                    Text(
                                        text = "Pin code",
                                        style = MaterialTheme.typography.caption.copy(
                                            color = colorResource(
                                                id = R.color.card_text
                                            ), fontSize = 12.sp
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                },
                                modifier = Modifier.size(width = width.dp, height = 50.dp)
                            )
                        }
                        SpacerHeight(value = 10.dp)
                        OutlinedTextField(
                            value = country,
                            textStyle = MaterialTheme.typography.body1.copy(
                                color = colorResource(id = R.color.text_color)
                            ),
                            onValueChange = { country = it },
                            placeholder = {
                                Text(
                                    text = "Country", style = MaterialTheme.typography.caption.copy(
                                        color = colorResource(
                                            id = R.color.card_text
                                        ), fontSize = 12.sp
                                    ), modifier = Modifier.weight(1f)
                                )
                            },
                            modifier = Modifier
                                .size(
                                    width = (configuration.screenWidthDp).dp, height = 50.dp
                                )
                                .padding(horizontal = 10.dp)
                        )
                        SpacerHeight(value = 10.dp)
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        ) {
                            Button(onClick = {
                                completeAddress =
                                    "$addressLine, $district, $state, $country, $pinCode"
                                isAddressEditable = false
                            }) {
                                Text(text = "Save")
                                SpacerWidth(value = 10.dp)
                                Image(
                                    painter = painterResource(id = R.drawable.check_icon),
                                    contentDescription = "",
                                    modifier = Modifier.size(18.dp),
                                    colorFilter = ColorFilter.tint(
                                        Color.White
                                    )
                                )
                            }
                        }

                    }
                }
            }
            SpacerHeight(value = 10.dp)
            AnimatedVisibility(visible = isEditable) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.card_bg),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(15.dp)
                ) {

                    Text(
                        text = "Auto detection of geolocation",
                        style = MaterialTheme.typography.body2.copy(
                            color = colorResource(
                                id = R.color.text_color
                            ), fontSize = 12.sp
                        )
                    )
                    CustomSwitch(checked = isLocationActive, onCheckedChange = { isLocationActive = !isLocationActive })
                }
            }

            SpacerHeight(value = 10.dp)
            Text(
                text = stringResource(id = R.string.cards),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body2,
                color= colorResource(id = R.color.text_color)
            )
            SpacerHeight(value = 10.dp)
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())

            ) {
                repeat(cardCount) {
                    Cards(isEditable = isEditable) {
                    }
                    SpacerWidth(value = 10.dp)
                }
                if (isEditable) {
                    AddCards { ++cardCount }
                }

            }
            AnimatedVisibility(visible = !isEditable) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SpacerHeight(value = 10.dp)
                    Text(
                        text = stringResource(id = R.string.trans),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body2,
                        color= colorResource(id = R.color.text_color)
                    )
                    SpacerHeight(value = 10.dp)
                    repeat(3) {
                        Transactions(
                            itemList = listOf(
                                RestaurantDishModel(
                                    restaurantName = "Homemade Pizza\nPepperoni",
                                    restaurantOffers = "",
                                    stars = 4.0f,
                                    comments = 261,
                                    sales = 1367,
                                    icon = R.drawable.pepperoni_pizza,
                                    isRestaurant = false
                                ), RestaurantDishModel(
                                    restaurantName = "Philadelphia rolls\nwith salmon",
                                    restaurantOffers = "",
                                    stars = 4.0f,
                                    comments = 285,
                                    sales = 1286,
                                    icon = R.drawable.salmon,
                                    isRestaurant = false
                                ), RestaurantDishModel(
                                    restaurantName = "Philadelphia rolls\n with salmon",
                                    restaurantOffers = "",
                                    stars = 4.0f,
                                    comments = 285,
                                    sales = 1286,
                                    icon = R.drawable.salmon,
                                    isRestaurant = false
                                ), RestaurantDishModel(
                                    restaurantName = "Philadelphia rolls\n with salmon",
                                    restaurantOffers = "",
                                    stars = 4.0f,
                                    comments = 285,
                                    sales = 1286,
                                    icon = R.drawable.salmon,
                                    isRestaurant = false
                                )
                            )
                        )
                    }
                }
            }
            SpacerHeight(value = 10.dp)
            AnimatedVisibility(visible = isEditable) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(id = R.color.card_bg),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(15.dp)
                            .clickable {
                                navController.navigate(Screens.ForgotPassword.route)
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lock_close),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                        )
                        SpacerWidth(value = 10.dp)
                        Text(
                            text = "Change Password", style = MaterialTheme.typography.body2.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ), fontSize = 12.sp
                            ), modifier = Modifier.weight(1f)
                        )
                        SpacerWidth(value = 10.dp)
                        Image(
                            painter = painterResource(id = R.drawable.ic_back),
                            modifier = Modifier
                                .size(20.dp)
                                .rotate(180f),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
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
                            .padding(15.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.moon_icon),
                            modifier = Modifier.size(20.dp),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color))
                        )
                        SpacerWidth(value = 10.dp)
                        Text(
                            text = "Dark Mode", style = MaterialTheme.typography.body2.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ), fontSize = 12.sp
                            ), modifier = Modifier.weight(1f)
                        )
                        SpacerWidth(value = 10.dp)
                        CustomSwitch(checked = isDarkActive, onCheckedChange = { isDarkActive=it })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AccountView() = Account(
    navController = rememberNavController()
)

@Composable
private fun SquareCard(
    icon: Int, text: String, onclick: () -> Unit
) {
    val configuration = LocalConfiguration.current

    val cardSize = ((configuration.screenWidthDp / 2) - 30).dp
    Column(modifier = Modifier
        .clickable {
            onclick()
        }
        .size(cardSize)
        .background(
            color = colorResource(id = R.color.card_bg),
            shape = MaterialTheme.shapes.medium.copy(
                CornerSize(15.dp)
            )
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariant)
        )
        UIElements().SpacerHeight(value = 20.dp)
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = colorResource(id = R.color.text_color),
            textAlign = TextAlign.Center
        )

    }
}