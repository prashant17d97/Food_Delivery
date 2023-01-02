package com.prashant.fooddelivery.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun Search(navController: NavController) {
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
            restaurantName = "Philadelphia rolls\nwith salmon",
            restaurantOffers = "",
            stars = 4.0f,
            comments = 285,
            sales = 1286,
            icon = R.drawable.salmon,
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
        )*/
    )
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        with(UIElements()) {
            SearchCard(
                value = searchValue,
                onValueChange = { searchValue = it },
                charLimit = 50,
                textStyle = MaterialTheme.typography.body2.copy(color = colorResource(id = R.color.card_text)),
                backGround = colorResource(id = R.color.card_bg),
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
                leadingIcon = Icons.Outlined.Search,
                placeHolderColor = colorResource(id = R.color.card_text),
                leadingIconColor = colorResource(id = R.color.card_text),
                trailingIcon = ImageVector.vectorResource(id = R.drawable.filter_icon),
                trailingIconRequire = true,
                trailingIconColor = colorResource(id = R.color.card_text),
                placeHolder = stringResource(id = R.string.search_hint),
                action = {},
                onTrailingClick = {},
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    items(count = dishes.size, itemContent = { item ->
                        UIElements().RestaurantDishCard(dishes[item])
                    })
                }
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    items(count = dishes.size, itemContent = { item ->
                        UIElements().RestaurantDishCard(dishes[item])
                    })
                }
            )


        }
    }
}

@Preview
@Composable
fun SearchView() = Search(navController = rememberNavController())