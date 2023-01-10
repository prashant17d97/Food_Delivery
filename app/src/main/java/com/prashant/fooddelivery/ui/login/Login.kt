package com.prashant.fooddelivery.ui.login

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.uielement.UIElements
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements

@Composable
fun Login(navController: NavController) {
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finishAffinity()
    }
    with(uiElements) {
        ImageBackground(painter = painterResource(R.drawable.login_pasta), backClick = {}) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.body1.copy(fontSize = 34.sp, color = Color.White),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(40.dp))
            CustomTextField(phone = phone, onValueChange = {
                if (it.isEmpty() || it.matches(pattern)) {
                    phone = it
                }
            })
            Spacer(modifier = Modifier.height(20.dp))
            CustomPasswordTextField(
                password = password,
                onValueChange = {
                    password = it
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screens.ForgotPassword.route) },
                text = "${stringResource(id = R.string.forgot_pass)}?",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(30.dp))

            GradientButtonNoRipple(
                textOnButton = stringResource(id = R.string.login),
                onClick = {
                    navController.navigate(Screens.Home.route) {
                        this.popUpTo(Screens.Login.route) {
                            this.inclusive = true
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screens.Registration.route)
                    },
                text = stringResource(id = R.string.create_new_account),
                style = MaterialTheme.typography.body1,
                color = colorResource(id = R.color._F88600),
                textAlign = TextAlign.Center
            )
        }
    }


}

@Preview
@Composable
fun LoginView() = Login(navController = rememberNavController())