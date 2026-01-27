package com.example.criminalalertapp.ui.animatedsplashscreen.navigation

import androidx.navigation.NavGraphBuilder
import com.example.criminalalertapp.ui.animatedsplashscreen.SplashScreen
import com.example.criminalalertapp.util.navigation.NavigationRoute
import com.example.criminalalertapp.util.navigation.glanceFade
import kotlinx.serialization.Serializable

@Serializable
internal data object SplashRoute : NavigationRoute()

internal fun NavGraphBuilder.splashScreen(
    onTimeout: () -> Unit
) {
    glanceFade<SplashRoute>
    {
        SplashScreen(onTimeout = onTimeout)
    }
}