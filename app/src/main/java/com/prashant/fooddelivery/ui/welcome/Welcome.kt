package com.prashant.fooddelivery.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.enums.IsVisible
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.name
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun Welcome(navController: NavController) {
    with(UIElements()) {
        ImageBackground(
            painter = painterResource(
                id = R.drawable.welcome_bg
            ),
            isBackVisible = IsVisible.GONE,
            columnTopPadding = 0.dp,
            columnVerticalArrangement = Arrangement.SpaceBetween,
            columnHorizontalAlign = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_welcome),
                    contentDescription = "Welcome",
                    modifier = Modifier
                        .size(200.dp)
                )
                val name = navController.currentBackStackEntry?.arguments?.getString(name)?:""
                Text(
                    text = "${stringResource(id = R.string.welcome)} ${(name.split(" "))[0]}",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 30.sp, color = colorResource(
                            id = R.color._566265
                        )
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier
                        .width(290.dp),
                    text = stringResource(id = R.string.welcome_message),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontSize = 12.sp, color = colorResource(
                            id = R.color._566265
                        )
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.Login.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(
                        id = R.color.white
                    )
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Lets go!", style = MaterialTheme.typography.body1.copy(
                        color = colorResource(
                            id = R.color.ic_launcher_background
                        ),
                        fontSize = 14.sp
                    )
                )
            }

        }
    }
}

@Preview
@Composable
fun WelcomeView() = Welcome(navController = rememberNavController())