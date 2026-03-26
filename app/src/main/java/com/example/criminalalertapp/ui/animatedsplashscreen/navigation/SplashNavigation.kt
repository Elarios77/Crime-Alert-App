package com.example.criminalalertapp.ui.animatedsplashscreen.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.criminalalertapp.ui.animatedsplashscreen.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

internal fun NavGraphBuilder.splashScreen(
    onTimeout: () -> Unit
) {
    composable<SplashRoute> {
        SplashScreen(onTimeout = onTimeout)
    }
}