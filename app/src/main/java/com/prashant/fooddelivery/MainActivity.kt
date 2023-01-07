package com.prashant.fooddelivery

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.prashant.fooddelivery.navigation.Screens
import com.prashant.fooddelivery.navigation.SetupNavGraph
import com.prashant.fooddelivery.theme.FoodDeliveryTheme
import java.lang.ref.WeakReference

class MainActivity : ComponentActivity() {
    private val isVisibility = MutableLiveData(true)

    companion object {
        lateinit var context: WeakReference<Context>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        context = WeakReference(this)
        /*.apply {
                this.setOnExitAnimationListener { viewProvider ->
                    viewProvider.iconView
                        .animate()
                        .setDuration(500L)
                        .alpha(0f)
                        .withEndAction {
                            viewProvider.remove()

                        }.start()
                }
            }*/
        isVisibility.observe(this) {
            window.navigationBarColor = if (it) {
                this.getColor(R.color.card_bg)
            } else {
                this.getColor(R.color.app_bg)
            }
        }

        setContent {
            FoodDeliveryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(rememberNavController()) {
                        isVisibility.value = it
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        context.clear()
    }
}


@Composable
private fun MainScreen(navHostController: NavHostController, isBNMVisible: (Boolean) -> Unit = {}) {
    isBNMVisible(showBnm(navHostController))
    Scaffold(
        bottomBar = {
            if (showBnm(navHostController)) {
                BottomNavigationView(navController = navHostController)
            }
        },
    ) {
        it.calculateTopPadding()
        Column(modifier = Modifier.padding(bottom = if (showBnm(navHostController)) 58.dp else 0.dp)) {
            SetupNavGraph(navController = navHostController)
        }
    }
}

@Composable
private fun showBnm(navHostController: NavHostController): Boolean {
    return when (currentRoute(navHostController)) {
        Screens.Home.route -> true
        Screens.Tracking.route -> true
        Screens.Checkout.route -> true
        Screens.Account.route -> true
        Screens.ItemPage.route -> true
        Screens.RestaurantsPage.route -> true
        else -> false
    }
}

@Composable
private fun BottomNavigationView(navController: NavController) {
    val items = listOf(
        Screens.Home, Screens.Tracking, Screens.Checkout, Screens.Account
    )
    BottomNavigation(
        /**modifier = Modifier
        .padding(10.dp)
        .graphicsLayer(
        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        clip = true,
        ),*/
        backgroundColor = colorResource(
            id = R.color.card_bg
        ),
        contentColor = colorResource(
            id = R.color.bnm_non_selected_icon_color
        ),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(icon = {
                Icon(
                    painterResource(id = item.icon), contentDescription = item.title
                )
            },
                /* label = {
                     Text(
                         text = item.title, color = colorResource(
                             id = if (currentRoute == item.route) R.color._FB7500 else if (isSystemInDarkTheme()) R.color.white else R.color._353E40
                         ), fontSize = 9.sp
                     )
                 },*/
                selectedContentColor = colorResource(id = R.color._FB7500),
                unselectedContentColor = colorResource(
                    id = if (isSystemInDarkTheme()) R.color.white else R.color._353E40
                ),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                inclusive = true
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
fun Bnm() = MainScreen(rememberNavController())