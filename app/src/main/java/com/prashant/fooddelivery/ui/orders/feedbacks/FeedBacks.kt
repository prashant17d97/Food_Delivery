package com.prashant.fooddelivery.ui.orders.feedbacks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.navigation.Screens

@Composable
fun FeedBacks(navController: NavController) {
    Image(painter = painterResource(id = R.drawable.user_icon),
        contentDescription = "",
        modifier = Modifier.clickable { navController.navigate(Screens.Login.route) })
}

@Preview
@Composable
fun FeedBacksView() = FeedBacks(navController = rememberNavController())