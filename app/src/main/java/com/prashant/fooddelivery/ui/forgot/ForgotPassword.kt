package com.prashant.fooddelivery.ui.forgot

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.enums.IsVisible
import com.prashant.fooddelivery.navigation.restPassword
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements

@Composable
fun ForgotPassword(navController: NavController) {
    var phone by rememberSaveable { mutableStateOf("") }
    with(uiElements) {
        ImageBackground(
            painter = painterResource(id = R.drawable.forgot_password),
            isBackVisible = IsVisible.VISIBLE,
            backClick = {
                Log.e("TAG", "ImageBackground: backClick()")
                navController.popBackStack()
            },
            columnVerticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.lock_open),
                modifier = Modifier.size(120.dp), contentDescription = "Forget"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.forgot_pass),
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.forgot_pass_msg),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                phone = phone,
                onValueChange = {
                    if (it.isEmpty() || it.matches(pattern)) {
                        phone = it
                    }
                },
                leadingIcon = ImageVector.vectorResource(
                    id = R.drawable.phone_icon
                ),
                imeAction = ImeAction.Done,
                charLimit = 10,
                singleLine = true,

                )
            Spacer(modifier = Modifier.height(30.dp))
            GradientButtonNoRipple(
                textOnButton = stringResource(id = R.string.send),
                onClick = {
                    navController.navigate(Screens.OTP.requireArguments(restPassword,"")) {
                        this.popUpTo(Screens.ForgotPassword.route) {
                            this.inclusive = true
                        }
                    }
                },
            )
        }
    }

}

@Preview
@Composable
fun ForgotPasswordView() = ForgotPassword(navController = rememberNavController())