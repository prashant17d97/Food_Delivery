package com.prashant.fooddelivery.ui.tracking

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.checkout
import com.prashant.fooddelivery.navigation.comingFrom
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements

@Composable
fun Tracking(navController: NavController) {
    val from = navController.currentBackStackEntry?.arguments?.getString(comingFrom)
    if (from != checkout) {
        BackHandler {
            navController.navigate(Screens.Home.route) {
                popUpTo(Screens.Tracking.route) {
                    inclusive = true
                }
            }
        }
    }
    Log.e("TAG", "Tracking: $from")
    if (from == checkout) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (background, foreground) = createRefs()
            val painter = painterResource(id = R.drawable.map_page)
            Image(painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
//                    .fillMaxHeight()
                    .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                    .fillMaxSize()
                    .constrainAs(background) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

            Column(modifier = Modifier
                .fillMaxSize()
                .constrainAs(foreground) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                }
            }


        }

    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.icon_tracking),
                modifier = Modifier
                    .size(300.dp)
                    .padding(start = 20.dp),
                contentDescription = "No Tracking"
            )
            uiElements.SpacerHeight(value = 10.dp)
            Text(
                text = "No order available for tracking",
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.text_color)
            )
        }
    }
}

@Preview
@Composable
fun TrackingView() = Tracking(navController = rememberNavController())