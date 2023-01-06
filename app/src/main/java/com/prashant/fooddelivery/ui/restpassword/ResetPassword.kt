package com.prashant.fooddelivery.ui.restpassword

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.enums.IsVisible
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun ResetPassword(navController: NavController) {
    var newPassword by rememberSaveable { mutableStateOf("") }
    with(UIElements()){
        ImageBackground(
            painter = painterResource(id = R.drawable.password_bg),
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
                text = stringResource(id = R.string.new_password),
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.create_new_pass),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomPasswordTextField(
                password = newPassword, onValueChange = { newPassword = it },
                leadingIcon = ImageVector.vectorResource(
                    id = R.drawable.lock_open
                ),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                singleLine = true,
                placeholderText = stringResource(id = R.string.new_password)
            )
            Spacer(modifier = Modifier.height(30.dp))
            GradientButtonNoRipple(
                textOnButton = stringResource(id = R.string.confirm_password),
                onClick = {
                    navController.navigate(Screens.Login.route) {
                        this.popUpTo(Screens.Login.route) {
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
fun ResetPasswordPreView() = ResetPassword(navController = rememberNavController())