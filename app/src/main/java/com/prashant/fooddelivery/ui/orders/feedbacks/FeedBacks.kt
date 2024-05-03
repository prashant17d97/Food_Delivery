package com.prashant.fooddelivery.ui.orders.feedbacks

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.methods.CommonMethod.fromJson
import com.prashant.fooddelivery.models.OrdersList
import com.prashant.fooddelivery.navigation.feedbackList
import com.prashant.fooddelivery.uielement.UIElements
import kotlinx.coroutines.launch

@Composable
fun FeedBacks(navController: NavController) {
    val list = navController.currentBackStackEntry?.arguments
        ?.getString(feedbackList)?.let {
            fromJson<OrdersList>(
                it
            )
        }
    var openDialog by remember {
        mutableStateOf(false) // Initially dialog is closed
    }
    val listState = rememberLazyListState()
    var currentIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()
    with(UIElements.uiElements) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color)),
                    contentDescription = "",
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
                Text(
                    text = "Leave feedback",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.body1.copy(
                        color = colorResource(
                            id = R.color.text_color
                        )
                    )
                )

            }
            SpacerHeight(value = 20.dp)

            LazyRow(
                state = listState,
               userScrollEnabled = false
            ) {
                if (list != null) {
                    items(list.list.size) { index ->
                        LeaveFeedbackCard(
                            isPreVisible = index > 0,
                            isNextVisible = index != list.list.size - 1,
                            onPrevious = {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(index = --currentIndex)
                                }
                            },
                            onNext = {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(index = ++currentIndex)
                                }
                            },
                            onSave = {
                                if (index != list.list.size - 1) {
                                    coroutineScope.launch {
                                        listState.animateScrollToItem(index = ++currentIndex)
                                    }
                                } else {
                                    openDialog = true
                                }
                            })
                        SpacerWidth(value = 10.dp)
                    }
                }
            }

        }
    }
    if (openDialog) {
        PopUp(
            dismiss = { /*openDialog = false*/ },
            openDialog = true,
            onClick = {
                openDialog = it
                navController.popBackStack()
            })
    }
}

@Preview
@Composable
fun FeedBacksView() = FeedBacks(navController = rememberNavController())

@Composable
fun PopUp(dismiss: () -> Unit, openDialog: Boolean, onClick: (Boolean) -> Unit) {
    Dialog(
        onDismissRequest = { dismiss() },
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .background(
                    color = colorResource(id = R.color.card_bg),
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 40.dp)
                    .background(
                        color = colorResource(id = R.color.card_bg),
                        shape = MaterialTheme.shapes.medium
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                with(UIElements.uiElements) {
                    SpacerHeight(value = 20.dp)
                    Text(
                        text = "Thanks for your\nfeedback!",
                        style = MaterialTheme.typography.body1.copy(
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    SpacerHeight(value = 20.dp)
                    androidx.compose.material.Text(
                        text = "You help us improve the quality\nof our service.",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center
                        ),
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    SpacerHeight(value = 20.dp)
                    OutlinedButton(
                        onClick = { onClick(!openDialog) },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
                        contentPadding = PaddingValues(0.dp),  //avoid the little icon
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.card_bg))
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = "Ok, back!", style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

            }
        }
    }
}