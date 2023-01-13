package com.prashant.fooddelivery.ui.otp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.prashant.fooddelivery.enums.IsVisible.VISIBLE
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.comingFrom
import com.prashant.fooddelivery.navigation.name
import com.prashant.fooddelivery.navigation.restPassword
import com.prashant.fooddelivery.uielement.UIElements.Companion.uiElements

@Composable
fun OTP(navController: NavController) {

    val from =
        navController.currentBackStackEntry?.arguments?.getString(comingFrom)?.removePrefix("{")
            ?: ""
    val name =
        (navController.currentBackStackEntry?.arguments?.getString(name))?.removeSuffix("}") ?: ""
    Log.e("TAG", "OTP: $from,$name")
    with(uiElements) {
        ImageBackground(
            painter = painterResource(id = R.drawable.otp_food),
            isBackVisible = VISIBLE,
            backClick = { navController.popBackStack() },
            columnVerticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sms),
                modifier = Modifier.size(120.dp), contentDescription = "Forget"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.sms_pass),
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.sms_pass_msg),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            DynamicOTP(4) { s, t ->
                Log.e("TAG", "OTP:$s, $t ")
            }
            Spacer(modifier = Modifier.height(30.dp))
            GradientButtonNoRipple(
                textOnButton = stringResource(id = R.string.verify),
                onClick = {
                    val route = if (from == restPassword) {
                        Screens.ResetPassword.route
                    } else {
                        Screens.Welcome.nameArgs(name)
                    }
                    navController.navigate(route) {
                        popUpTo(Screens.OTP.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun OTPPreView() = OTP(navController = rememberNavController())