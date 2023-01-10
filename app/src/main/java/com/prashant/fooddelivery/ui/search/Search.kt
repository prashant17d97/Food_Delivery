package com.prashant.fooddelivery.ui.search

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.uielement.CellCounts
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Search(navController: NavController) {
    var rating by rememberSaveable {
        mutableStateOf(0f)
    }
    var slider by rememberSaveable {
        mutableStateOf(0f)
    }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }
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
        with(uiElements) {
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
                onTrailingClick = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                },
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
            uiElements.VerticalGridCells(
                spanCount = CellCounts.TWO,
                list = dishes,
                modifier = Modifier.fillMaxWidth(),
                itemScope = {
                    uiElements.RestaurantDishCard(
                        it,
                        isBottomRowRequire = true,
                        isSpan = true,
                        onCardClick = {
                            navController.navigate(Screens.ItemPage.route)
                        })
                })
        }
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheet(
                rating = rating,
                slider = slider,
                sliderValues = { slider = it },
                ratingValues = {
                    rating = it
                    Log.e("TAG", "Search: $slider, $rating")
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        sheetShape = RoundedCornerShape(20.dp),
        sheetBackgroundColor = colorResource(id = R.color.card_bg),
        scrimColor = colorResource(id = R.color.scrimColor)
    ) {

    }
}

@Preview
@Composable
fun SearchView() = BottomSheet(0f, 10f)

@Composable
fun BottomSheet(
    rating: Float,
    slider: Float,
    ratingValues: (Float) -> Unit = {},
    sliderValues: (Float) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.card_bg),
                shape = MaterialTheme.shapes.medium.copy(CornerSize(20.dp))
            )
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Divider(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.filter_divider),
                    shape = MaterialTheme.shapes.medium.copy(CornerSize(20.dp))
                )
                .width(100.dp)
                .height(5.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cafe",
                style = MaterialTheme.typography.body1,
                color = colorResource(id = R.color.text_color)
            )
            Text(
                text = "Restaurants",
                style = MaterialTheme.typography.body1,
                color = colorResource(id = R.color.text_color)
            )
            Text(
                text = "Fast food",
                style = MaterialTheme.typography.body1,
                color = colorResource(id = R.color.text_color)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "1k  ",
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                color = colorResource(id = R.color.text_color)
            )
            Slider(
                modifier = Modifier.weight(1f),
                value = slider,
                onValueChange = { sliderValues(it) },
                valueRange = 1000f..10000f,
                steps = 20,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colors.primaryVariant,
                    activeTrackColor = MaterialTheme.colors.primaryVariant
                )
            )
            Text(
                text = "   10k",
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                color = colorResource(id = R.color.text_color)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Rating",
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 14.sp),
                color = colorResource(id = R.color.text_color)
            )
            RatingBar(
                value = rating,
                config = RatingBarConfig()
                    .activeColor(MaterialTheme.colors.primaryVariant)
                    .inactiveBorderColor(MaterialTheme.colors.primaryVariant)
                    .numStars(5)
                    .style(RatingBarStyle.HighLighted),
                onValueChange = {
                    ratingValues(it)
                },
                onRatingChanged = {
                    Log.d("TAG", "onRatingChanged: $it")
                }
            )
        }

    }
}