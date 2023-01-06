package com.prashant.fooddelivery.uielement

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
import com.prashant.fooddelivery.enums.IsVisible
import com.prashant.fooddelivery.methods.CommonMethod.toast
import com.prashant.fooddelivery.models.CategoriesModel
import com.prashant.fooddelivery.models.CommentModel
import com.prashant.fooddelivery.models.RestaurantDishModel
import com.prashant.fooddelivery.models.SuggestionModel


class UIElements {
    val pattern = Regex("^\\d+\$")

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
     * [CustomTextField]
     * */
    @OptIn(ExperimentalComposeUiApi::class)
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
                    color = backGround,
                    shape = MaterialTheme.shapes.medium
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
                            ImeAction.Done, ImeAction.Go, ImeAction.Search, ImeAction.Search -> {
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

    @OptIn(ExperimentalComposeUiApi::class)
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

    @Composable
    fun GradientButtonNoRipple(
        textOnButton: String, onClick: () -> Unit
    ) {
        val interactionSource = remember {
            MutableInteractionSource()
        }
        Box(
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
                }, contentAlignment = Alignment.Center
        ) {
            Text(
                text = textOnButton,
                style = MaterialTheme.typography.body1,
            )
        }

    }

    @OptIn(ExperimentalComposeUiApi::class)
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
                                    ImeAction.Done, ImeAction.Go, ImeAction.Search, ImeAction.Search -> {
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


    @Composable
    fun SearchRecentSuggestions(dataModel: SuggestionModel, value: (String) -> Unit) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { value(dataModel.dishName) }
                .padding(vertical = 5.dp, horizontal = 4.dp)
        ) {
            Text(
                text = dataModel.dishName,
                modifier = Modifier.weight(5F),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body2.copy(
                    color = colorResource(
                        id = R.color.card_text
                    ),
                    fontSize = 14.sp
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
                        ),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
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
                    style = MaterialTheme.typography.body1.copy(color = colorResource(id = R.color.text_color))
                )
            }

        }
    }

    @Composable
    fun RestaurantDishCard(
        restaurantDishModel: RestaurantDishModel,
        isBottomRowRequire: Boolean,
        isSpan: Boolean=false,
        onCardClick: () -> Unit = {}
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .clickable { onCardClick() }
                .width(180.dp)
                .padding(
                    end = if (restaurantDishModel.isRestaurant ) {
                        10.dp
                    } else {
                        if (isBottomRowRequire&& !isSpan) {
                            10.dp
                        } else {
                            0.dp
                        }
                    }, bottom = 10.dp
                ),
            backgroundColor = colorResource(id = R.color.card_bg)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = if (restaurantDishModel.isRestaurant) {
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp, vertical = 15.dp)
                } else {
                    Modifier
                        .fillMaxWidth()
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
                            color = colorResource(id = R.color.text_color),
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                RatingBar(
                    value = restaurantDishModel.stars,
                    config = RatingBarConfig()
                        .activeColor(MaterialTheme.colors.primaryVariant)
                        .inactiveBorderColor(MaterialTheme.colors.primaryVariant)
                        .numStars(5)
                        .size(14.dp)
                        .style(RatingBarStyle.HighLighted),
                    onValueChange = {

                    },
                    onRatingChanged = {
                        Log.d("TAG", "onRatingChanged: $it")
                    }
                )

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
                                    ),
                                    fontSize = 10.sp
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
                                    ),
                                    fontSize = 10.sp
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
            image = R.drawable.user_photo,
            name = "Prashant",
            date = "12-01-2023",
            comment = "h"
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
                        .size(64.dp)
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
                            text = commentModel.name , style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = commentModel.date, style = MaterialTheme.typography.body1.copy(
                                color = colorResource(
                                    id = R.color.text_color
                                ),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Light
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
                            ),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    @Preview
    @Composable
    fun PreviewUiElements() = CommentCard(
        CommentModel(
            image = R.drawable.user_photo,
            name = "Prashant",
            date = "12-01-2023",
            comment = "JB"
        )
    )
}

