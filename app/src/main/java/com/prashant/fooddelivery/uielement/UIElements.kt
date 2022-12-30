package com.prashant.fooddelivery.uielement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.prashant.fooddelivery.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomPasswordTextField(
    password: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    placeholderText: String = stringResource(id = R.string.password),
    placeholderColor: Color = colorResource(id = R.color.white),
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Password
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val icon =
        if (isPasswordVisible)
            painterResource(id = R.drawable.ic_visibile) else painterResource(id = R.drawable.ic_visibility_off)

    TextField(
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus(true)
            },
            onNext = {
                focusManager.moveFocus(
                    focusDirection = FocusDirection.Next
                )
            }),
        value = password,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = MaterialTheme.shapes.medium,

        modifier = Modifier
            .background(
                color = colorResource(id = R.color._33979797),
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White),
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.lock_open),
                contentDescription = "phoneIcon",
                tint = colorResource(id = R.color.white)
            )
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = icon,
                    contentDescription = "",
                    tint = colorResource(id = R.color.white)
                )
            }
        },
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = placeholderText,
                color = placeholderColor,
                style = MaterialTheme.typography.body2
            )
        },
    )
}


@Composable
fun CustomTextField(
    phone: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    charLimit: Int = 10,
    singleLine: Boolean = true
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = phone,

        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),

        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .background(
                color = colorResource(id = R.color._33979797),
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = "phoneIcon",
                tint = colorResource(id = R.color.white)
            )
        },

        onValueChange = {
            if (it.length == charLimit) {
                focusManager.moveFocus(
                    focusDirection = FocusDirection.Next
                )
            }
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.phone),
                color = colorResource(id = R.color.white),
                style = MaterialTheme.typography.body2
            )
        },
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = imeAction
        ),
    )
}