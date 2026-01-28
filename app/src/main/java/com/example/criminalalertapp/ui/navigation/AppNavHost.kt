package com.example.criminalalertapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.criminalalertapp.ui.animatedsplashscreen.navigation.SplashRoute
import com.example.criminalalertapp.ui.animatedsplashscreen.navigation.splashScreen
import com.example.criminalalertapp.ui.openmap.navigation.OpenMapRoute
import com.example.criminalalertapp.ui.openmap.navigation.openMapScreen
import com.example.criminalalertapp.ui.report.navigation.ReportRoute
import com.example.criminalalertapp.ui.report.navigation.reportScreen

@Composable
internal fun AppNavHost(

) {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        splashScreen(
            onTimeout = {
                navController.navigate(OpenMapRoute) {
                    popUpTo(SplashRoute) { inclusive = true }
                }
            }
        )
        openMapScreen(
            onNavigateToReport = {
                navController.navigate(ReportRoute)
            }
        )
        reportScreen()
    }
}