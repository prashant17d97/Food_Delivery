package com.prashant.fooddelivery.ui.otp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.uielement.CustomTextField
import com.prashant.fooddelivery.uielement.DynamicOTP
import com.prashant.fooddelivery.uielement.ImageBackground

@Composable
fun OTP(navController: NavController) {
    var phone = ""
    ImageBackground(
        painter = painterResource(id = R.drawable.otp_food),
        isBackVisible = true,
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
        DynamicOTP(4){s,t->
            Log.e("TAG", "OTP:$s, $t ", )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                navController.navigate(Screens.OTP.route) {
                    this.popUpTo(Screens.ForgotPassword.route) {
                        this.inclusive = true
                    }
                }
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth(),
            content = {
                Text(
                    text = stringResource(id = R.string.verify),
                    style = MaterialTheme.typography.button
                )
            }
        )
    }
}

@Preview
@Composable
fun OTPPreView() = OTP(navController = rememberNavController())