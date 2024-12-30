package com.jetbrains.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.TV_1080p
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.jetbrains.tv.presentation.DetailsScreen
import com.jetbrains.tv.presentation.DetailsScreenArgs
import com.jetbrains.tv.presentation.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { SampleNavigationDrawer(navController, drawerState) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            NavHost(navController = navController, startDestination = Screens.Home()) {
                composable(Screens.Home()) {
                    HomeScreen(onMovieSelected = {
                        navController.navigate(Screens.Details.withArgs(it.id))
                    })
                }
                composable(
                    route = Screens.Details(),
                    arguments = listOf(navArgument(DetailsScreenArgs.movieId) {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    val movieId = backStackEntry.arguments?.getInt(DetailsScreenArgs.movieId) ?: 1
                    DetailsScreen(
                        movieId = movieId,
                        onMovieSelected = {})
                }
                composable(Screens.Settings()) {
                    SettingsScreen()
                }
                composable(Screens.Favorites()) {
                    FavouritesScreen()
                }
            }
        }
    }
}

@Composable
fun SampleNavigationDrawer(navController: NavController, drawerState: DrawerState) {
    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf(
        "Home" to Icons.Default.Home,
        "Settings" to Icons.Default.Settings,
        "Favourites" to Icons.Default.Favorite
    )

    Column(
        Modifier
            .background(Color.Gray)
            .fillMaxHeight()
            .padding(12.dp)
            .selectableGroup(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items.forEachIndexed { index, item ->
            val (text, icon) = item

            NavigationDrawerItem(
                label = { Text(text) },
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    when (text) {
                        "Home" -> {
                            navController.navigate(Screens.Home()) {
                                popUpTo(Screens.Home()) { inclusive = true }
                            }
                        }

                        "Settings" -> {
                            navController.navigate(Screens.Settings()) {
                                popUpTo(Screens.Home()) { inclusive = true }
                            }
                        }

                        "Favourites" -> {
                            navController.navigate(Screens.Favorites()) {
                                popUpTo(Screens.Home()) { inclusive = true }
                            }
                        }
                    }
                    // Close the drawer after selection
//                    drawerState.close()
                }
            )
        }
    }
}


@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = " Setting Screen",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun FavouritesScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = " Favourites Screen",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

data class Movie(val id: Int)


@Preview(showBackground = true, device = TV_1080p)
@Composable
fun AppPreview() {
    App()
}
