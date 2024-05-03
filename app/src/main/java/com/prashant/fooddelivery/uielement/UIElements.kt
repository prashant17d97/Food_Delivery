package com.prashant.fooddelivery.uielement

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.enums.CellCounts
import com.prashant.fooddelivery.enums.IsVisible
import com.prashant.fooddelivery.methods.CommonMethod.toast
import com.prashant.fooddelivery.models.CategoriesModel
import com.prashant.fooddelivery.models.CommentModel
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.models.SuggestionModel

@OptIn(ExperimentalComposeUiApi::class)
class UIElements {
    val pattern = Regex("^\\d+\$")

    companion object {
        val uiElements = UIElements()
    }

    /**

    [CustomPasswordTextField] composable function that creates a custom password TextField with a leading icon and a password visibility toggle button.

    The function takes in the current password as a String, an onValueChange function that is called when the password is changed,

    and optional parameters for customizing the TextField's behavior and appearance.

     *@param password a required String value representing the current password displayed in the TextField.

     *@param onValueChange a required lambda function that is called whenever the value of the TextField is changed, passing the new password String.

     *@param singleLine an optional Boolean value that specifies whether the TextField should be single line or multi-line. Default is true (single line).

     *@param placeholderText an optional String value representing the placeholder text to be displayed when the TextField is empty. Default is "Password".

     *@param placeholderColor an optional Color value representing the color of the placeholder text. Default is white.

     *@param imeAction an optional ImeAction value representing the keyboard action to take when the TextField's keyboard is used. Default is ImeAction.Done.

     *@param keyboardType an optional KeyboardType value representing the type of keyboard to display. Default is KeyboardType.Password.

     *@param onGO an optional lambda function that is called when the keyboard's "Go" button is pressed. Default is an empty function.

     *@param leadingIcon an optional ImageVector representing the leading icon to display in the TextField. Default is an open lock icon.

     *@return a TextField composable with a custom background, shape, leading icon, password visibility toggle button, and password text.
     */
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
        val icon = if (isPasswordVisible) painterResource(id = R.drawable.ic_visibile)
        else painterResource(id = R.drawable.ic_visibility_off)

        TextField(
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType, imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(onDone = {
                isPasswordVisible = false
                keyboardController?.hide()
                focusManager.clearFocus(true)
            }, onNext = {
                isPasswordVisible = false
                focusManager.moveFocus(
                    focusDirection = FocusDirection.Next
                )
            }, onGo = {
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
     * [CustomTextField] composable function to render a customized text field with leading and trailing icons, background color, placeholder text, and other configurations.
     *@param phone The current value of the phone number field.
     *@param onValueChange A callback function to be triggered when the value of the phone number field changes.
     *@param keyboardType The type of keyboard to show, default is KeyboardType.Phone.
     *@param imeAction The action to take when the IME button is clicked, default is ImeAction.Next.
     *@param capitalization The type of capitalization to apply, default is KeyboardCapitalization.None.
     *@param charLimit The maximum character limit allowed in the field, default is 10.
     *@param textStyle The text style to apply, default is white color text.
     *@param backGround The background color to apply, default is semi-transparent white.
     *@param singleLine Determines if the text field should be limited to a single line.
     *@param trailingIconRequire Determines if a trailing icon should be displayed, default is false.
     *@param placeHolderColor The color to use for the placeholder text, default is white.
     *@param placeHolder The text to display as a placeholder when the field is empty, default is "Phone".
     *@param action A callback function to be triggered when the done button is clicked.
     *@param onTrailingClick A callback function to be triggered when the trailing icon is clicked.
     *@param leadingIcon The leading icon to display, default is a phone icon.
     *@param leadingIconColor The color to use for the leading icon, default is white.
     *@param trailingIcon The trailing icon to display, default is a phone icon.
     *@param trailingIconColor The color to use for the trailing icon, default is white.
     */
    @Composable
    fun CustomTextField(
        phone: String,
        onValueChange: (String) -> Unit,
        keyboardType: KeyboardType = KeyboardType.Phone,
        imeAction: ImeAction = ImeAction.Next,
        capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
        charLimit: Int = 10,
        textStyle: TextStyle = TextStyle(color = Color.White),
        backGround: Color = colorResource(id = R.color._66FFFFFF),
        singleLine: Boolean = true,
        trailingIconRequire: Boolean = false,
        placeHolderColor: Color = colorResource(id = R.color.white),
        placeHolder: String = stringResource(id = R.string.phone),
        action: () -> Unit = {},
        onTrailingClick: () -> Unit = {},
        leadingIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.phone_icon),
        leadingIconColor: Color = colorResource(id = R.color.white),
        trailingIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.phone_icon),
        trailingIconColor: Color = colorResource(id = R.color.white),
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
                    color = backGround, shape = MaterialTheme.shapes.medium
                )
                .fillMaxWidth(),
            textStyle = textStyle,
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "leadingIcon",
                    tint = leadingIconColor
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
                },
                onSearch = {
                    action()
                    keyBoardControl?.hide()
                    focusManager.clearFocus(true)
                },
                onSend = {
                    action()
                    keyBoardControl?.hide()
                    focusManager.clearFocus(true)
                },
            ),
            trailingIcon = {
                if (trailingIconRequire) {
                    IconButton(onClick = { onTrailingClick() }) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = "trailingIcon",
                            tint = trailingIconColor
                        )
                    }
                }
            },
            onValueChange = {
                when {
                    it.length == charLimit -> {
                        when (imeAction) {
                            ImeAction.Next -> {
                                focusManager.moveFocus(
                                    focusDirection = FocusDirection.Next
                                )
                            }
                            ImeAction.Done, ImeAction.Go, ImeAction.Search -> {
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
                    text = placeHolder,
                    color = placeHolderColor,
                    style = MaterialTheme.typography.body2
                )
            },
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType, imeAction = imeAction, capitalization = capitalization
            ),
        )
    }

    /**
     * [ImageBackground] composable function that displays an image as a background with a foreground column of content.
     *@param painter The [Painter] object that represents the image to be displayed as the background.
     *@param columnStartPadding The padding value for the start of the foreground column.
     *@param columnEndPadding The padding value for the end of the foreground column.
     *@param columnTopPadding The padding value for the top of the foreground column.
     *@param columnBottomPadding The padding value for the bottom of the foreground column.
     *@param isBackVisible The visibility state of a back button placed at the top left corner of the foreground column.
     *@param backPaddingModifier The padding value for the back button.
     *@param columnVerticalArrangement The vertical arrangement of the content within the foreground column.
     *@param columnHorizontalAlign The horizontal alignment of the content within the foreground column.
     *@param backClick The click listener for the back button.
     *@param content The composable function that defines the content of the foreground column.*/

    @Composable
    fun ImageBackground(
        painter: Painter,
        columnStartPadding: Dp = 30.dp,
        columnEndPadding: Dp = 30.dp,
        columnTopPadding: Dp = 100.dp,
        columnBottomPadding: Dp = 0.dp,
        isBackVisible: IsVisible = IsVisible.GONE,
        backPaddingModifier: Dp = 20.dp,
        columnVerticalArrangement: Arrangement.Vertical = Arrangement.Center,
        columnHorizontalAlign: Alignment.Horizontal = Alignment.CenterHorizontally,
        backClick: () -> Unit = {},
        content: @Composable ColumnScope.() -> Unit,
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (background, foreground) = createRefs()

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

            Column(modifier = Modifier
                .fillMaxSize()
                .constrainAs(foreground) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                if (isBackVisible.boolean) {
                    IconButton(onClick = { backClick() }, content = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back),
                            modifier = Modifier.padding(backPaddingModifier),
                            contentDescription = "Navigate To back"
                        )
                    })

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
                        ),
                    verticalArrangement = columnVerticalArrangement,
                    horizontalAlignment = columnHorizontalAlign
                ) {
                    content()
                }
            }


        }

    }

    /**
    [DynamicOTP] composable function that renders a dynamic OTP input field.
     *@param size The number of input fields to be displayed.

     *@param otp A function that takes a String and an Int parameter representing the entered OTP value and its index respectively.

     *@return A composable UI that renders the dynamic OTP input fields.
     */
    @Composable
    fun DynamicOTP(size: Int, otp: (String, Int) -> Unit) {
        val focusManager = LocalFocusManager.current
        val keyBoardControl = LocalSoftwareKeyboardController.current
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(size) {
                var tempOt by rememberSaveable {
                    mutableStateOf("")
                }
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .padding(5.dp)
                ) {
                    TextField(
                        placeholder = {
                            Text(
                                text = "${it + 1}", style = MaterialTheme.typography.body2.copy(
                                    textAlign = TextAlign.Center,
                                )

                            )
                        },
                        value = tempOt,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = if (it != size - 1) {
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
                            textAlign = TextAlign.Center, color = Color.White
                        ),

                        keyboardActions = KeyboardActions(onDone = {}, onNext = {}, onGo = {}),
                        onValueChange = { value ->
                            if (value.length == 1) {
                                tempOt = value.last().toString()
                                otp(tempOt, it)
                            } else {
                                tempOt = if (value.isNotEmpty()) value.last().toString() else ""
                                otp(tempOt, it)
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


    /**
    [GradientButtonNoRipple] composable function that creates a GradientButton with no ripple effect. The button can have an optional leading and trailing icon,
    along with a text displayed on it. The button is clickable and calls a function when clicked.
     *@param leadingIcon an optional Int value that represents the drawable resource ID of the leading icon to be displayed on the button. Default is 0 (no icon).
     *@param trailingIcon an optional Int value that represents the drawable resource ID of the trailing icon to be displayed on the button. Default is 0 (no icon).
     *@param textOnButton a required String value that represents the text to be displayed on the button.
     *@param onClick a required lambda function that will be called when the button is clicked.
     *@return a Row composable with a gradient background, the specified icon(s), and text on the button. The button is clickable and calls a function when clicked.
     */
    @Composable
    fun GradientButtonNoRipple(
        leadingIcon: Int = 0,
        trailingIcon: Int = 0,
        textOnButton: String, onClick: () -> Unit,
    ) {
        val interactionSource = remember {
            MutableInteractionSource()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color._F88600), colorResource(id = R.color._FB7500)
                        )
                    ), shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable(
                    indication = null, // Assign null to disable the ripple effect
                    interactionSource = interactionSource
                ) {
                    onClick()
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != 0) {
                Image(
                    painter = painterResource(id = leadingIcon), contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            SpacerWidth(value = 10.dp)
            Text(
                text = textOnButton,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            SpacerWidth(value = 10.dp)
            if (trailingIcon != 0) {
                Image(
                    painter = painterResource(id = leadingIcon), contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }

    }


    /**
    [SearchCard] composable function that displays a card with a search bar, which provides search suggestions while typing.
    *@param value The current value of the search bar.
    *@param onValueChange A function that takes a string and is called whenever the value of the search bar changes.
    *@param keyboardType The type of keyboard to use for the search bar. The default is set to KeyboardType.Phone.
    *@param imeAction The type of action to take when the IME action button is pressed. The default is set to ImeAction.Next.
    *@param capitalization The type of capitalization to use for the search bar. The default is set to KeyboardCapitalization.None.
    *@param charLimit The maximum number of characters allowed in the search bar. The default is set to 10.
    *@param textStyle The style to use for the text in the search bar. The default is set to white.
    *@param backGround The background color of the search bar card. The default is set to the card_bg color resource.
    *@param singleLine A boolean indicating whether the search bar should be limited to a single line. The default is set to true.
    *@param shape The shape to use for the search bar card. The default is set to MaterialTheme.shapes.medium.
    *@param trailingIconRequire A boolean indicating whether a trailing icon is required for the search bar. The default is set to false.
    *@param placeHolderColor The color of the search bar placeholder text. The default is set to white.
    *@param placeHolder The text to display as the search bar placeholder. The default is set to "phone".
    *@param action A function to be executed when the IME action button is pressed. The default is set to an empty function.
    *@param onTrailingClick A function to be executed when the trailing icon is clicked. The default is set to an empty function.
    *@param onCardClick A function to be executed when the search bar card is clicked. The default is set to an empty function.
    *@param enabled A boolean indicating whether the search bar should be enabled. The default is set to true.
    *@param leadingIcon An ImageVector to use as the search bar leading icon. The default is set to the phone_icon.
    *@param leadingIconColor The color of the search bar leading icon. The default is set to white.
    *@param trailingIcon An ImageVector to use as the search bar trailing icon. The default is set to the filter_icon.
    *@param trailingIconColor The color of the search bar trailing icon. The default is set to white.
     */
    @Composable
    fun SearchCard(
        value: String,
        onValueChange: (String) -> Unit,
        keyboardType: KeyboardType = KeyboardType.Phone,
        imeAction: ImeAction = ImeAction.Next,
        capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
        charLimit: Int = 10,
        textStyle: TextStyle = TextStyle(color = Color.White),
        backGround: Color = colorResource(id = R.color.card_bg),
        singleLine: Boolean = true,
        shape: Shape = MaterialTheme.shapes.medium,
        trailingIconRequire: Boolean = false,
        placeHolderColor: Color = colorResource(id = R.color.white),
        placeHolder: String = stringResource(id = R.string.phone),
        action: () -> Unit = {},
        onTrailingClick: () -> Unit = {},
        onCardClick: () -> Unit = {},
        enabled: Boolean = true,
        leadingIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.phone_icon),
        leadingIconColor: Color = colorResource(id = R.color.white),
        trailingIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.filter_icon),
        trailingIconColor: Color = colorResource(id = R.color.white),
    ) {
        var viewSuggestion by remember {
            mutableStateOf(false)
        }

        Card(
            backgroundColor = backGround,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCardClick() },
            shape = shape

        ) {
            val focusManager = LocalFocusManager.current
            val keyBoardControl = LocalSoftwareKeyboardController.current
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                TextField(
                    value = value,
                    enabled = enabled,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = backGround,
                        focusedIndicatorColor = backGround,
                        unfocusedIndicatorColor = backGround,
                        disabledIndicatorColor = backGround
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backGround)
                        .clickable { onCardClick() },
                    textStyle = textStyle,
                    leadingIcon = {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = "leadingIcon",
                            tint = leadingIconColor
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            action()
                            viewSuggestion = false
                            keyBoardControl?.hide()
                            focusManager.clearFocus(true)
                        },
                        onNext = {
                            focusManager.moveFocus(
                                focusDirection = FocusDirection.Next
                            )
                        },
                        onGo = {
                            viewSuggestion = false
                            action()
                            keyBoardControl?.hide()
                            focusManager.clearFocus(true)
                        },
                        onSearch = {
                            viewSuggestion = false
                            action()
                            keyBoardControl?.hide()
                            focusManager.clearFocus(true)
                        },
                        onSend = {
                            viewSuggestion = false
                            action()
                            keyBoardControl?.hide()
                            focusManager.clearFocus(true)
                        },
                    ),
                    trailingIcon = {
                        if (trailingIconRequire) {
                            IconButton(onClick = {
                                keyBoardControl?.hide()
                                focusManager.clearFocus(true)
                                onTrailingClick()
                            }) {
                                Icon(
                                    imageVector = trailingIcon,
                                    contentDescription = "trailingIcon",
                                    tint = trailingIconColor
                                )
                            }
                        }
                    },
                    onValueChange = {
                        viewSuggestion = it.isNotEmpty()
                        when {
                            it.length == charLimit -> {
                                when (imeAction) {
                                    ImeAction.Next -> {
                                        focusManager.moveFocus(
                                            focusDirection = FocusDirection.Next
                                        )
                                    }
                                    ImeAction.Done, ImeAction.Go, ImeAction.Search -> {
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
                            text = placeHolder,
                            color = placeHolderColor,
                            style = MaterialTheme.typography.body2
                        )
                    },
                    singleLine = singleLine,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction,
                        capitalization = capitalization
                    ),
                )
                AnimatedVisibility(visible = viewSuggestion) {
                    Divider(
                        color = colorResource(id = R.color._9AAAAE),
                        thickness = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(top = 10.dp)
                    ) {
                        val search = listOf(
                            SuggestionModel(dishName = "Pizza Market Bistro", distance = 5.0),
                            SuggestionModel(dishName = "Sushi", distance = 7.3),
                            SuggestionModel(dishName = "Mexican food", distance = 2.3),
                            SuggestionModel(dishName = "Pizza", distance = 1.0),
                        )
                        repeat(4) { i ->
                            SearchRecentSuggestions(search[i]) {
                                onValueChange(it)
                            }
                        }
                    }
                }
            }


        }
    }

    /**

    Displays a row with dish name and distance in kilometers for a suggestion.
    *@param dataModel SuggestionModel representing the suggestion data to be displayed.
    *@param value Lambda function to be executed when the suggestion row is clicked.
     */
    @Composable
    fun SearchRecentSuggestions(dataModel: SuggestionModel, value: (String) -> Unit) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { value(dataModel.dishName) }
                .padding(vertical = 5.dp, horizontal = 4.dp)) {
            Text(
                text = dataModel.dishName,
                modifier = Modifier.weight(5F),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body2.copy(
                    color = colorResource(
                        id = R.color.card_text
                    ), fontSize = 14.sp
                )
            )
            Row(
                modifier = Modifier.weight(1F),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${dataModel.distance} KM.",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = colorResource(
                            id = R.color.card_text
                        ), fontSize = 12.sp, fontWeight = FontWeight.Light
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(

                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location",
                    tint = colorResource(
                        id = R.color.card_text
                    ),
                    modifier = Modifier.size(14.dp)
                )
            }

        }
    }

    /**

    [CategoryCard] composable function that displays a category card.
    *@param categoryModel the data model representing the category.
     */
    @Composable
    fun CategoryCard(categoryModel: CategoriesModel) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .size(width = 90.dp, height = 80.dp)
                .padding(end = 10.dp),
            backgroundColor = colorResource(id = R.color.card_bg)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = categoryModel.icon),
                    contentDescription = categoryModel.categoriesName,
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = categoryModel.categoriesName,
                    style = MaterialTheme.typography.body1.copy(
                        color = colorResource(id = R.color.text_color), fontSize = 12.sp
                    )
                )
            }

        }
    }

    /**

    [RestaurantDishCard] composable function that displays a card view representing a restaurant dish.
    *@param restaurantDishModel The restaurant dish model to display.
    *@param isBottomRowRequire Whether to display a row at the bottom of the card.
    *@param paddingEnd The padding to apply at the end of the card.
    *@param onCardClick The callback function to be invoked when the card is clicked.
     */
    @Composable
    fun RestaurantDishCard(
        restaurantDishModel: RestaurantDishModel,
        isBottomRowRequire: Boolean,
        paddingEnd: Dp = 0.dp,
        onCardClick: () -> Unit = {}
    ) {
        val configuration = LocalConfiguration.current
        val itemWidth = ((configuration.screenWidthDp) * 0.9) / if (configuration.orientation ==
            Configuration.ORIENTATION_PORTRAIT
        ) 2 else 4

        Card(shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .clickable { onCardClick() }
                .width(itemWidth.dp)
                .padding(end = paddingEnd, bottom = 10.dp),
            backgroundColor = colorResource(id = R.color.card_bg)) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = if (restaurantDishModel.isRestaurant) {
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp, vertical = 15.dp)
                } else {
                    Modifier.fillMaxWidth()
                }
            ) {
                Image(
                    modifier = if (restaurantDishModel.isRestaurant) {
                        Modifier
                            .width(80.dp)
                            .height(80.dp)
                    } else {
                        Modifier
                            .fillMaxWidth(1f)
                            .height(80.dp)
                    },
                    contentScale = if (restaurantDishModel.isRestaurant) {
                        ContentScale.Fit
                    } else {
                        ContentScale.FillBounds
                    },
                    painter = painterResource(id = restaurantDishModel.icon),
                    contentDescription = restaurantDishModel.restaurantName,
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(),
                    text = restaurantDishModel.restaurantName,
                    style = MaterialTheme.typography.body1.copy(
                        color = colorResource(id = R.color.text_color),
                    ),
                    textAlign = TextAlign.Center
                )
                if (restaurantDishModel.restaurantOffers.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = restaurantDishModel.restaurantOffers,
                        style = MaterialTheme.typography.body2.copy(
                            color = colorResource(id = R.color.text_color), fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                RatingBar(value = restaurantDishModel.stars,
                    config = RatingBarConfig().activeColor(MaterialTheme.colors.primaryVariant)
                        .inactiveBorderColor(MaterialTheme.colors.primaryVariant).numStars(5)
                        .size(14.dp).style(RatingBarStyle.HighLighted),
                    onValueChange = {

                    },
                    onRatingChanged = {
                        Log.d("TAG", "onRatingChanged: $it")
                    })

                if (isBottomRowRequire) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_comment),
                                contentDescription = "Comment"
                            )
                            Text(
                                text = " ${restaurantDishModel.comments}",
                                style = MaterialTheme.typography.subtitle1.copy(
                                    color = colorResource(
                                        id = R.color.text_color
                                    ), fontSize = 10.sp
                                )
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_sale),
                                contentDescription = "Sale"
                            )
                            Text(
                                text = " ${restaurantDishModel.sales}",
                                style = MaterialTheme.typography.subtitle1.copy(
                                    color = colorResource(
                                        id = R.color.text_color
                                    ), fontSize = 10.sp
                                )
                            )
                        }
                    }
                }
            }
        }

    }

    @Composable
    fun CommentCard(
        commentModel: CommentModel = CommentModel(
            image = R.drawable.user_photo, name = "Prashant", date = "12-01-2023", comment = "h"
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(commentModel.image),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                        .clip(CircleShape)// clip to the circle shape
                        .weight(0.7f),
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(3.2f)
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = commentModel.name, style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ), fontSize = 14.sp, fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = commentModel.date, style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ), fontSize = 12.sp, fontWeight = FontWeight.Light
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Great meal, fast delivery! Everything was delicious. I also liked how it was packaged and served, thanks!",
                        style = MaterialTheme.typography.body1.copy(
                            color = colorResource(
                                id = R.color.text_color
                            ), fontSize = 12.sp, fontWeight = FontWeight.Light
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    /**

    [CheckoutItemCard] function displays a card with information about an item in the checkout list.
    *@param modifier modifier to be applied to the card.
    *@param isCheckOutPage boolean indicating if the card is being displayed in the checkout page.
    *@param color background color of the card.
    *@param onCardClick callback to be called when the card is clicked.
     */
    @Composable
    fun CheckoutItemCard(
        modifier: Modifier = Modifier,
        isCheckOutPage: Boolean = true,
        color: Color = MaterialTheme.colors.primaryVariant,
        onCardClick: () -> Unit = {}
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = color,
                        shape = MaterialTheme.shapes.medium
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(0.9f)
                        .background(
                            color = colorResource(id = R.color.card_bg),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wavos),
                        modifier = Modifier.size(width = 70.dp, height = 50.dp),
                        contentDescription = ""
                    )
                    SpacerWidth(10.dp)
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {

                        Text(
                            text = "Wavos rencheros", style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ), fontWeight = FontWeight.SemiBold, fontSize = 14.sp
                            )
                        )
                        SpacerHeight(value = 5.dp)
                        Text(
                            text = "Wavos rencheros", style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ), fontWeight = FontWeight.Light, fontSize = 14.sp
                            )
                        )
                    }
                    SpacerWidth(20.dp)
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "$ 5,25",
                        style = MaterialTheme.typography.body1.copy(
                            color = colorResource(
                                id = R.color.price_color
                            ), fontWeight = FontWeight.SemiBold, fontSize = 14.sp
                        ),
                        textAlign = TextAlign.End
                    )
                }

                if (isCheckOutPage) {
                    IconButton(onClick = { onCardClick() }) {
                        Image(
                            modifier = Modifier.weight(0.1f),
                            painter = painterResource(id = R.drawable.close_icon),
                            contentDescription = ""
                        )
                    }
                }

            }
            SpacerHeight(10.dp)
        }
    }

    /**
     * [SpacerHeight] is used to give height.
     * @param value requires for the height.
     * */
    @Composable
    fun SpacerHeight(value: Dp) {
        Spacer(modifier = Modifier.height(value))
    }

    /**
     * [SpacerWidth] is used to give width.
     * @param value requires for the width.
     * */
    @Composable
    fun SpacerWidth(value: Dp) {
        Spacer(modifier = Modifier.width(value))
    }


    /**
     * [CustomSwitch] is completely custom which requires the following params.
     * @param width
     * @param height it is optional and it is better not assign value for it coz,
     * it calculate best value from width.
     * @param activeColor this parameter is used for assign color for active mode switch
     * @param inActiveColor this parameter is used for assign color for inActive mode switch
     * @param checked this parameter is for initial value
     * @param onCheckedChange this parameter is change the state of switch on-off-on.
     * @author Prashant Kumar Singh
     * @since 2023
     * @return It returns switch scope with it boolean state.
     * */
    @Composable
    fun CustomSwitch(
        width: Int = 40,
        height: Int = (width * 0.5).toInt(),
        activeColor: Color = colorResource(R.color.text_color),
        inActiveColor: Color = colorResource(R.color.text_color),
        checked: Boolean = true,
        onCheckedChange: (Boolean) -> Unit
    ) {
        val padding: Int = (height / 2)
        val dotSize = (height * 0.75).toFloat()
        val color = if (checked) activeColor else inActiveColor
        Row(horizontalArrangement = if (checked) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(width = width.dp, height = height.dp)
                .border(BorderStroke(2.dp, color = color), shape = CircleShape)
                .padding(padding.dp)
                .clickable {
                    onCheckedChange(!checked)

                }) {
            Canvas(modifier = Modifier, onDraw = {
                drawCircle(
                    color = color,
                    radius = dotSize,
                    style = Fill,
                )
            })
        }
    }


    @Composable
    fun <Generic> VerticalGridCells(
        modifier: Modifier = Modifier,
        spanCount: CellCounts = CellCounts.TWO,
        list: List<Generic>,
        itemScope: @Composable (Generic) -> Unit
    ) {
        val listSize = list.size
        var index = 0
        val configuration = LocalConfiguration.current
        val span =
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) spanCount.int else spanCount.int * 2
        val totalRow =
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) (listSize + 1) / span else ((listSize + 1) / span) + 1

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Log.e("TAG", "GridCells: $listSize **** $totalRow")
            for (row in 1..totalRow) {
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = {
                        items(count = span, itemContent = {
                            if (index < listSize) {
                                itemScope(list[index])
                            }
                            index++
                        })
                    })
            }
        }
    }

    @Composable
    fun <Generic> Order(
        itemList: List<Generic>,
        isOnGoing: Boolean = true,
        onClick: (Boolean) -> Unit = {}
    ) {
        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }
        /**
         *
         */
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = if (isExpanded) RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp
                        ) else RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = "July 5, 2019",
                    style = MaterialTheme.typography.subtitle1,
                    color = colorResource(
                        id = R.color.text_color
                    )
                )
                Text(
                    text = "18:44",
                    style = MaterialTheme.typography.subtitle1,
                    color = colorResource(
                        id = R.color.text_color
                    )
                )
                Text(
                    text = if (isOnGoing) {
                        "Current order"
                    } else {
                        "Delivered"
                    },
                    style = MaterialTheme.typography.subtitle1,
                    color = colorResource(
                        id = R.color.text_color
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_up),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .rotate(if (!isExpanded) 180f else 0f)


                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.card_bg),
                            shape = RoundedCornerShape(
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        )
                ) {
                    repeat(itemList.size) {
                        CheckoutItemCard(isCheckOutPage = false, onCardClick = {})
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(id = R.color.card_bg),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.time_icon),
                                    contentDescription = "Sale",
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "  40 m.", style = MaterialTheme.typography.body1.copy(
                                        color = colorResource(
                                            id = R.color.text_color
                                        ), fontSize = 12.sp
                                    )
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.wallet_icon),
                                    contentDescription = "Sale",
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "  $1,575", style = MaterialTheme.typography.body1.copy(
                                        color = colorResource(
                                            id = R.color.price_color
                                        ), fontSize = 12.sp
                                    )
                                )
                            }
                        }
                        SpacerHeight(value = 20.dp)
                        OutlinedButton(
                            onClick = {
                                onClick(isOnGoing)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
                            contentPadding = PaddingValues(0.dp),  //avoid the little icon
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.card_bg))
                        ) {
                            if (isOnGoing) {
                                Icon(
                                    modifier = Modifier.padding(end = 10.dp),
                                    painter = painterResource(id = R.drawable.place_icon),
                                    contentDescription = "Trace",
                                    tint = MaterialTheme.colors.primaryVariant,
                                )
                            }
                            Text(
                                modifier = Modifier.padding(start = 10.dp),
                                text = stringResource(id = if (isOnGoing) R.string.trace else R.string.leave_feedback),
                                style = MaterialTheme.typography.body1.copy(
                                    color = MaterialTheme.colors.primaryVariant,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                        }
                        SpacerHeight(value = 20.dp)

                    }
                }
            }
            SpacerHeight(value = 10.dp)
        }
    }

    @Composable
    fun <Generic> Transactions(
        itemList: List<Generic>,
        onClick: (Boolean) -> Unit = {}
    ) {
        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }
        /**
         *
         */
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(true) }
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.margarita_logo),
                        modifier = Modifier.size(40.dp),
                        contentDescription = "Restaurants Logo"
                    )
                    Text(
                        text = "July 5, 2019",
                        style = MaterialTheme.typography.subtitle1,
                        color = colorResource(
                            id = R.color.text_color
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "$155.25",
                        style = MaterialTheme.typography.subtitle1,
                        color = colorResource(
                            id = R.color.text_color
                        ),
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_up),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable { isExpanded = !isExpanded }
                            .rotate(if (!isExpanded) 180f else 0f)


                    )
                }
                AnimatedVisibility(visible = isExpanded) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        repeat(itemList.size) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 10.dp,
                                        end = 20.dp,
                                        top = 5.dp
                                    )
                            ) {
                                Text(
                                    text = "Peperoni Homemade Pizza",
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        color = colorResource(id = R.color.text_color),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                                Text(
                                    text = "$50.67",
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        color = colorResource(id = R.color.text_color),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                        SpacerHeight(value = 10.dp)
                        Text(
                            text = "October 11, 2019",
                            style = MaterialTheme.typography.subtitle1.copy(
                                color = colorResource(id = R.color.text_color),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Light
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            textAlign = TextAlign.Start
                        )
                        SpacerHeight(value = 10.dp)
                    }
                }

            }
            SpacerHeight(value = 10.dp)
        }

    }

    @Composable
    fun Cards(
        isEditable: Boolean = true,
        click: () -> Unit
    ) {
        var cardNumber by rememberSaveable {
            mutableStateOf("")
        }
        var editCard by rememberSaveable {
            mutableStateOf(false)
        }
        var cardExp by rememberSaveable {
            mutableStateOf("")
        }
        var cvv by rememberSaveable {
            mutableStateOf("")
        }
        val configuration = (LocalConfiguration.current).screenWidthDp
        val width = (configuration * 0.75)

        ConstraintLayout(
            modifier = Modifier
                .width(width.dp)
                .height(186.54546.dp)
                .background(
                    color = colorResource(id = R.color.card_bg),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(10.dp)
        ) {
            val (icon, close, button, card, date, cvvConstraint) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.user_image),
                modifier = Modifier
                    .size(40.dp)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentDescription = ""
            )
            AnimatedVisibility(visible = isEditable, modifier = Modifier.constrainAs(close) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(icon.bottom)
            }) {
                Image(
                    painter = painterResource(
                        id = if (editCard) R.drawable.close_icon else R.drawable
                            .ic_edit
                    ),
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { editCard = !editCard },
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.text_color)),
                    contentDescription = ""
                )
            }
            OutlinedTextField(
                value = cardNumber,
                enabled = editCard,
                textStyle = MaterialTheme.typography.body1.copy(
                    color = colorResource(id = R.color.text_color)
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .constrainAs(card) {
                        top.linkTo(icon.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onValueChange = { cardNumber = it },
                placeholder = {
                    Text(
                        text = "**** **** **** 1234",
                        style = MaterialTheme.typography.body2.copy(
                            color = colorResource(
                                id = R.color.card_text
                            ), fontSize = 12.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                //modifier = Modifier.size(width = (configuration*0.2).dp, height = 50.dp)
            )
            OutlinedTextField(
                value = cardExp,
                enabled = editCard,
                textStyle = MaterialTheme.typography.body1.copy(
                    color = colorResource(id = R.color.text_color)
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { cardExp = it },
                placeholder = {
                    Text(
                        text = "11/12", style = MaterialTheme.typography.body2.copy(
                            color = colorResource(
                                id = R.color.card_text
                            ), fontSize = 12.sp
                        ), modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier
                    .size(
                        width = (configuration * 0.2).dp,
                        height = 60.dp
                    )
                    .constrainAs(date) {
                        top.linkTo(card.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 10.dp)
            )

            OutlinedTextField(
                value = cvv,
                enabled = editCard,
                textStyle = MaterialTheme.typography.body1.copy(
                    color = colorResource(id = R.color.text_color)
                ),
                onValueChange = { cvv = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                placeholder = {
                    Text(
                        text = "123", style = MaterialTheme.typography.body2.copy(
                            color = colorResource(
                                id = R.color.card_text
                            ), fontSize = 12.sp
                        ), modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier
                    .size(
                        width = (configuration * 0.2).dp,
                        height = 60.dp
                    )
                    .constrainAs(cvvConstraint) {
                        top.linkTo(card.bottom)
                        if (editCard) {
                            start.linkTo(date.end)
                            end.linkTo(button.start)
                        } else {
                            end.linkTo(parent.end)
                        }
                    }
                    .padding(top = 10.dp)
            )

            AnimatedVisibility(visible = editCard, modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(card.bottom)
                    end.linkTo(parent.end)
                }
                .padding(top = 10.dp)) {
                Button(onClick = {
                    editCard = false
                    click()
                }) {
                    Text(text = "Save")
                    SpacerWidth(value = 10.dp)
                    Image(
                        painter = painterResource(id = R.drawable.check_icon),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(
                            Color.White
                        )
                    )
                }
            }

        }
    }

    @Composable
    fun AddCards(onClick: () -> Unit) {
        val configuration = (LocalConfiguration.current).screenWidthDp
        val width = (configuration * 0.75)

        ConstraintLayout(
            modifier = Modifier
                .width(width.dp)
                .height(186.54546.dp)
                .background(
                    color = colorResource(id = R.color.card_bg),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(10.dp)
        ) {
            val (add) = createRefs()

            Icon(imageVector = Icons.Outlined.Add, contentDescription = "", modifier = Modifier
                .constrainAs(add) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(40.dp)
                .clickable { onClick() }
                .background(
                    color = colorResource(id = R.color.card_bg), shape = MaterialTheme
                        .shapes.medium
                ),
                tint = colorResource(id = R.color.text_color)
            )

        }
    }

    @Composable
    fun LeaveFeedbackCard(
        isPreVisible: Boolean,
        isNextVisible: Boolean,
        onPrevious: () -> Unit,
        onNext: () -> Unit = {},
        onSave: () -> Unit = {},
    ) {
        val width = (LocalConfiguration.current).screenWidthDp
        var feedback by rememberSaveable {
            mutableStateOf("")
        }
        var ratingValue by rememberSaveable {
            mutableStateOf(0f)
        }
        ConstraintLayout(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.card_bg), shape = MaterialTheme
                        .shapes.medium
                )
                .width(width = (width - 20).dp)
        ) {
            val (items, feedbackBox, ratingText, rating, button, prev, prevIcon, nextIcon, next) =
                createRefs()

            CheckoutItemCard(isCheckOutPage = false, color = Color.Transparent,
                modifier = Modifier
                    .width(width = width.dp)
                    .padding(10.dp)
                    .constrainAs(items) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

            TextField(
                value = feedback,
                onValueChange = { feedback = it },
                placeholder = {
                    Text(
                        text = "Your feedback.", style = MaterialTheme.typography.body2.copy(
                            textAlign = TextAlign.Start,
                            color = colorResource(id = R.color.card_text)

                        )
                    )
                },
                textStyle = MaterialTheme.typography.body1.copy(color = colorResource(id = R.color.text_color)),
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(width = width.dp)
                    .height(150.dp)
                    .padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 10.dp)
                    .constrainAs
                        (feedbackBox) {
                        top.linkTo(items.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "Your rating", style = MaterialTheme.typography.body1.copy(
                    textAlign = TextAlign.Start,
                    color = colorResource(id = R.color.text_color)
                ),
                modifier = Modifier
                    .padding(start = 30.dp, end = 0.dp, top = 10.dp, bottom = 10.dp)
                    .constrainAs(ratingText) {
                        top.linkTo(feedbackBox.bottom)
                        bottom.linkTo(rating.bottom)
                        start.linkTo(feedbackBox.start)
                    }
            )

            RatingBar(value = ratingValue,
                modifier = Modifier
                    .padding(
                        start = 0.dp, end = 30.dp, top = 10.dp, bottom = 10
                            .dp
                    )
                    .constrainAs(rating) {
                        top.linkTo(feedbackBox.bottom)
                        end.linkTo(feedbackBox.end)
                    },
                config = RatingBarConfig().activeColor(MaterialTheme.colors.primaryVariant)
                    .inactiveBorderColor(MaterialTheme.colors.primaryVariant).numStars(5)
                    .size(30.dp).style(RatingBarStyle.HighLighted),
                onValueChange = {
                    ratingValue = it
                },
                onRatingChanged = {
                    Log.d("TAG", "onRatingChanged: $it")
                })

            OutlinedButton(
                onClick = { onSave() },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(width.dp)
                    .padding(horizontal = 20.dp)
                    .constrainAs(button) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(rating.bottom)
                    },
                border = BorderStroke(0.5.dp, MaterialTheme.colors.primaryVariant),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.card_bg))
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Leave feedback", style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            if (isPreVisible) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 5.dp, top = 10.dp, bottom = 10.dp)
                        .constrainAs
                            (prevIcon) {
                            start.linkTo(parent.start)
                            top.linkTo(button.bottom)
                        })
                Text(
                    text = "Previous feedback", style = MaterialTheme.typography.body1.copy(
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.text_color)
                    ),
                    modifier = Modifier
                        .clickable { onPrevious() }
                        .constrainAs
                            (prev) {
                            start.linkTo(prevIcon.end)
                            top.linkTo(prevIcon.top)
                            bottom.linkTo(prevIcon.bottom)
                        })
            }


            if (isNextVisible) {
                Text(
                    text = "Next dish", style = MaterialTheme.typography.body1.copy(
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.primaryVariant
                    ),
                    modifier = Modifier
                        .clickable { onNext() }
                        .constrainAs
                            (next) {
                            end.linkTo(nextIcon.start)
                            top.linkTo(nextIcon.top)
                            bottom.linkTo(nextIcon.bottom)
                        })
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    tint = MaterialTheme.colors.primaryVariant,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                        .rotate(180f)
                        .constrainAs
                            (nextIcon) {
                            top.linkTo(button.bottom)
                            end.linkTo(parent.end)
                        })
            }

        }
    }


    @Preview
    @Composable
    fun PreviewUIElement() {
    }
}


