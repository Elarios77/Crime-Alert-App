package com.example.criminalalertapp.ui.openmap.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.criminalalertapp.ui.openmap.screen.MapScreen
import kotlinx.serialization.Serializable

@Serializable
object OpenMapRoute

internal fun NavGraphBuilder.openMapScreen() {
    composable<OpenMapRoute> {
        MapScreen()
    }
}