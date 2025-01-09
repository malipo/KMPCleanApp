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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { SampleNavigationDrawer(navController, drawerState) }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Jetpack Compose TV App") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Open Drawer")
                        }
                    }
                )
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    NavHost(navController = navController, startDestination = Screens.Home()) {
                        composable(Screens.Home()) {
                            HomeScreen(onMovieSelected = { movie ->
                                navController.navigate(Screens.Details.withArgs(movie.id))
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
                                onMovieSelected = {}
                            )
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
        )
    }
}

@Composable
fun SampleNavigationDrawer(navController: NavController, drawerState: DrawerState) {
    var selectedIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope() // Use the appropriate CoroutineScope

    val items = listOf(
        "Home" to Icons.Default.Home,
        "Settings" to Icons.Default.Settings,
        "Favourites" to Icons.Default.Favorite
    )

    Column(
        Modifier
            .background(Color.DarkGray)
            .fillMaxHeight()
            .padding(12.dp)
            .selectableGroup(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items.forEachIndexed { index, item ->
            val (text, icon) = item

            NavigationDrawerItem(
                label = { Text(text, color = Color.White) },
                icon = { Icon(icon, contentDescription = null) },
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    coroutineScope.launch {
                        when (text) {
                            "Home" -> {
                                navController.navigate(Screens.Home()) {
                                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                                }
                            }
                            "Settings" -> {
                                navController.navigate(Screens.Settings()) {
                                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                                }
                            }
                            "Favourites" -> {
                                navController.navigate(Screens.Favorites()) {
                                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                                }
                            }
                        }
                        drawerState.close()
                    }
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
            text = "Settings Screen",
            color = Color.Black,
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
            text = "Favourites Screen",
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
