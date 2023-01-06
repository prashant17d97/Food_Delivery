package com.prashant.fooddelivery.ui.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.methods.CommonMethod.toast
import com.prashant.fooddelivery.navigation.RegistrationScreen
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.uielement.UIElements

@Composable
fun Registration(navController: NavController) {
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    with(UIElements()) {
        ImageBackground(
            painter = painterResource(R.drawable.registration_pizza),
            columnTopPadding = 0.dp,
            columnVerticalArrangement = Arrangement.Center,
            columnHorizontalAlign = Alignment.CenterHorizontally,
            backClick = {}
        ) {
            ConstraintLayout {
                val (image, uploadButton) = createRefs()
                Image(painter = painterResource(id = R.drawable.ic_photo_upload),
                    contentDescription = "User Image",
                    modifier = Modifier.constrainAs(image) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    })
                Image(painter = painterResource(id = R.drawable.ic_upload),
                    contentDescription = "Upload Image",
                    modifier = Modifier.constrainAs(uploadButton) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    })
            }

            Spacer(modifier = Modifier.height(50.dp))
            CustomTextField(
                phone = name,
                onValueChange = { name = it },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                charLimit = 30,
                capitalization = KeyboardCapitalization.Words,
                singleLine = true,
                placeHolder = stringResource(id = R.string.name),
                leadingIcon = ImageVector.vectorResource(id = R.drawable.user_icon)
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                phone = phone,
                onValueChange = {
                    if (it.isEmpty() || it.matches(pattern)) {
                        phone = it
                    }
                },
                imeAction = ImeAction.Next,
                charLimit = 10,
                singleLine = true,
                leadingIcon = ImageVector.vectorResource(id = R.drawable.phone_icon)
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomPasswordTextField(
                password = password,
                onValueChange = { password = it },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomPasswordTextField(
                password = confirmPassword,
                onValueChange = { confirmPassword = it },
                leadingIcon = ImageVector.vectorResource(id = R.drawable.lock_close),
                placeholderText = stringResource(id = R.string.confirm_password),
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(50.dp))
            GradientButtonNoRipple(
                textOnButton = stringResource(id = R.string.registration),
                onClick = {
                    if (validate(
                            name.trim(),
                            number = phone,
                            password = password,
                            confirmPassword = confirmPassword
                        )
                    ) {
                        navController.navigate(
                            Screens.OTP.requireArguments(
                                RegistrationScreen,
                                name
                            )
                        ) {
                            this.popUpTo(Screens.Registration.route) {
                                this.inclusive = true
                            }
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append(stringResource(id = R.string.already_have_ac))
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primaryVariant)) {
                        append("  ${stringResource(id = R.string.login)}")

                    }
                },
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        }
    }

}

@Preview
@Composable
fun RegistrationPreview() = Registration(navController = rememberNavController())

fun validate(name: String, number: String, password: String, confirmPassword: String): Boolean {
    return when {
        name.isBlank() -> {
            toast("Name field is not allowed to be empty")
            false
        }
        number.isBlank() -> {
            toast("Number field is not allowed to be empty")
            false
        }
        password.isBlank() -> {
            toast("Please enter confirm password")
            false
        }
        confirmPassword.isBlank() -> {
            toast("Please enter confirm password")
            false
        }
        else -> true
    }
}

