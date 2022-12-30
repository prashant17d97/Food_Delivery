package com.prashant.fooddelivery.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.R
import com.prashant.fooddelivery.uielement.CustomPasswordTextField
import com.prashant.fooddelivery.uielement.CustomTextField

@Composable
fun Login(navController: NavController) {
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (background, foreground) = createRefs()
        val painter = painterResource(R.drawable.login_pasta)
        Image(painter = painterResource(id = R.drawable.login_pasta),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(White, Color.Black),
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 30.dp, top = 150.dp, end = 30.dp)
                .constrainAs(foreground) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomTextField(phone = phone, onValueChange = { phone = it })
            Spacer(modifier = Modifier.height(20.dp))
            CustomPasswordTextField(
                password = password,
                onValueChange = {
                    password = it
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.forgot_pass),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { /*TODO*/ },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.button
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.create_new_account),
                style = MaterialTheme.typography.body1,
                color = colorResource(id = R.color._F88600),
                textAlign = TextAlign.Center
            )
        }


    }


}

@Preview
@Composable
fun LoginView() = Login(navController = rememberNavController())