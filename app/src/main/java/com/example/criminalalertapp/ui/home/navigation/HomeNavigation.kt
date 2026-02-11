package com.example.criminalalertapp.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import com.example.criminalalertapp.ui.home.screen.HomeScreen
import com.example.criminalalertapp.util.navigation.NavigationRoute
import com.example.criminalalertapp.util.navigation.glanceFade
import kotlinx.serialization.Serializable

@Serializable
internal data object HomeRoute: NavigationRoute()

internal fun NavGraphBuilder.homeScreen(){
    glanceFade<HomeRoute> {
        HomeScreen()
    }
}