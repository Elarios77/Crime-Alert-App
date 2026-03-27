package com.example.criminalalertapp.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import com.example.criminalalertapp.ui.home.screen.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavGraphBuilder.homeScreen() {
    composable<HomeRoute> {
        HomeScreen()
    }
}