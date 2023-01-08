package com.prashant.fooddelivery.ui.account

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun Account(navController: NavController) {
    var name by rememberSaveable {
        mutableStateOf("Jenifer")
    }
    var isEditable by rememberSaveable {
        mutableStateOf(false)
    }

    val editIcon = if (isEditable) R.drawable.ic_setting_edit else R.drawable.ic_setting
    with(UIElements()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
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
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.app_bg))
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
                Image(
                    painter = painterResource(id = editIcon), contentDescription = "",
                    modifier = Modifier.clickable { isEditable = !isEditable }
                )
            }
            SpacerHeight(value = 20.dp)
            TextField(
                value = name, onValueChange = { name = it },
                enabled = isEditable,
                textStyle = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )

            AnimatedVisibility(visible = !isEditable) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SquareCard(
                        icon = R.drawable.like_icon,
                        text = "Saved \nDishes"
                    )
                    SquareCard(
                        icon = R.drawable.restaurants_icon,
                        text = "Saved \nRestaurants"
                    )
                }
            }
            SpacerHeight(value = 15.dp)
            Text(
                text = stringResource(id = R.string.delivery_address), modifier = Modifier
                    .fillMaxWidth(), textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body2
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
                        .padding(horizontal = 20.dp)
                ) {

                    Text(
                        text = "Auto detection of geolocation",
                        style = MaterialTheme.typography.body2.copy(
                            color = colorResource(
                                id = R.color.text_color
                            ), fontSize = 12.sp
                        )
                    )
                    Switch(checked = true, onCheckedChange = {})
                }
            }

            SpacerHeight(value = 10.dp)
            Text(
                text = stringResource(id = R.string.cards), modifier = Modifier
                    .fillMaxWidth(), textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body2
            )
            SpacerHeight(value = 10.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}

@Preview
@Composable
fun AccountView() = Account(
    navController =
    rememberNavController()
)

@Composable
fun SquareCard(
    icon: Int,
    text: String
) {
    val configuration = LocalConfiguration.current

    val cardSize = ((configuration.screenWidthDp / 2) - 30).dp
    Log.e("TAG", "Account: ${cardSize},${configuration.screenWidthDp}")
    Column(
        modifier = Modifier
            .size(cardSize)
            .background(
                color = colorResource(id = R.color.card_bg),
                shape = MaterialTheme.shapes.medium.copy(
                    CornerSize(15.dp)
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon), contentDescription = "",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariant)
        )
        UIElements().SpacerHeight(value = 20.dp)
        Text(
            text = "Saved Dishes",
            style = MaterialTheme.typography.body1,
            color = colorResource(id = R.color.text_color)
        )

    }
}