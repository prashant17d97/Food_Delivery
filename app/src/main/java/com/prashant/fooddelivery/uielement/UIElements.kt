package com.prashant.fooddelivery.uielement

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.methods.CommonMethod.toast

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomPasswordTextField(
    password: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    placeholderText: String = stringResource(id = R.string.password),
    placeholderColor: Color = colorResource(id = R.color.white),
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Password,
    onGO: () -> Unit = {},
    leadingIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.lock_open)
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val icon =
        if (isPasswordVisible)
            painterResource(id = R.drawable.ic_visibile)
        else painterResource(id = R.drawable.ic_visibility_off)

    TextField(
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                isPasswordVisible = false
                keyboardController?.hide()
                focusManager.clearFocus(true)
            },
            onNext = {
                isPasswordVisible = false
                focusManager.moveFocus(
                    focusDirection = FocusDirection.Next
                )
            },
            onGo = {
                isPasswordVisible = false
                onGO()
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
                color = colorResource(id = R.color._66FFFFFF),
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
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


/**
 * [CustomTextField]
 * */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    phone: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    charLimit: Int = 10,
    singleLine: Boolean = true,
    action: () -> Unit = {},
    leadingIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.phone_icon)
) {
    val focusManager = LocalFocusManager.current
    val keyBoardControl = LocalSoftwareKeyboardController.current
    TextField(
        value = phone,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),

        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .background(
                color = colorResource(id = R.color._66FFFFFF),
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "phoneIcon",
                tint = colorResource(id = R.color.white)
            )
        },
        keyboardActions = KeyboardActions(
            onDone = {
                action()
                keyBoardControl?.hide()
                focusManager.clearFocus(true)
            },
            onNext = {
                focusManager.moveFocus(
                    focusDirection = FocusDirection.Next
                )
            },
            onGo = {
                action()
                keyBoardControl?.hide()
                focusManager.clearFocus(true)
            }
        ),
        onValueChange = {
            when {
                it.length == charLimit -> {
                    when (imeAction) {
                        ImeAction.Done -> {
                            action()
                            keyBoardControl?.hide()
                            focusManager.clearFocus(true)
                        }
                        ImeAction.Next -> {
                            focusManager.moveFocus(
                                focusDirection = FocusDirection.Next
                            )
                        }
                        ImeAction.Go -> {
                            action()
                            keyBoardControl?.hide()
                            focusManager.clearFocus(true)
                        }
                    }
                    onValueChange(it)
                }
                it.length <= charLimit -> {
                    onValueChange(it)
                }
                else -> {
                    toast(message = "You can not add more character")
                    when (imeAction) {
                        ImeAction.Done -> {
                            keyBoardControl?.hide()
                            focusManager.clearFocus()
                        }
                        ImeAction.Next -> {
                            focusManager.moveFocus(
                                focusDirection = FocusDirection.Next
                            )
                        }
                    }

                }
            }


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
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
    )
}

/**
 * This [ImageBackground] compose will return the full screen with image background with column Scope.
 * It will require an image in painterResource
 * */
@Composable
fun ImageBackground(
    painter: Painter,
    columnStartPadding: Dp = 30.dp,
    columnEndPadding: Dp = 30.dp,
    columnTopPadding: Dp = 100.dp,
    columnBottomPadding: Dp = 0.dp,
    isBackVisible: Boolean = false,
    backClick: () -> Unit = {},
    backPaddingModifier: Dp = 20.dp,
    columnVerticalArrangement: Arrangement.Vertical = Arrangement.Center,
    columnHorizontalAlign: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (background, foreground, backNavigator) = createRefs()

        Image(painter = painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.White, Color.Black),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
                .fillMaxSize()
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        AnimatedVisibility(visible = isBackVisible) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Navigate To back",
                modifier = Modifier
                    .clickable { backClick() }
                    .padding(backPaddingModifier)
                    .constrainAs(backNavigator) {
                        top.linkTo(background.top)
                        start.linkTo(background.start)
                    }
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = columnStartPadding,
                    end = columnEndPadding,
                    top = columnTopPadding,
                    bottom = columnBottomPadding
                )
                .constrainAs(foreground) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalArrangement = columnVerticalArrangement,
            horizontalAlignment = columnHorizontalAlign
        ) {
            content()
        }


    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DynamicOTP(size: Int,otp:(String,Int)->Unit) {
    val focusManager = LocalFocusManager.current
    val keyBoardControl = LocalSoftwareKeyboardController.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(size) {
            var tempOt by rememberSaveable() {
                mutableStateOf("")
            }
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(5.dp)
            ) {
                TextField(
                    value = tempOt,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (it != size - 1) {
                            ImeAction.Next
                        } else {
                            ImeAction.Done
                        }
                    ),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color._66FFFFFF),
                            shape = MaterialTheme.shapes.medium
                        )
                        .fillMaxSize(),
                    textStyle = MaterialTheme.typography.body1.copy(
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),

                    keyboardActions = KeyboardActions(
                        onDone = {},
                        onNext = {
                        },
                        onGo = {
                        }
                    ),
                    onValueChange = { value ->
                        if (value.length == 1) {
                            tempOt = value.last().toString()
                            otp(tempOt,it)
                        } else {
                            tempOt = if (value.isNotEmpty()) value.last().toString() else ""
                            otp(tempOt,it)
                        }
                        if (value.isNotEmpty()) {
                            if (it != size - 1) {
                                focusManager.moveFocus(
                                    focusDirection = FocusDirection.Next
                                )
                            } else {
                                keyBoardControl?.hide()
                                focusManager.clearFocus()
                            }

                        } else {
                            if (it != 0) {
                                focusManager.moveFocus(
                                    focusDirection = FocusDirection.Previous
                                )
                            }
                        }
                    },
                    singleLine = true,
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewUiElements() = DynamicOTP(4, otp = {s,t->})

