package com.example.criminalalertapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.criminalalertapp.ui.animatedsplashscreen.navigation.SplashRoute
import com.example.criminalalertapp.ui.animatedsplashscreen.navigation.splashScreen
import com.example.criminalalertapp.ui.home.navigation.HomeRoute
import com.example.criminalalertapp.ui.home.navigation.homeScreen
import com.example.criminalalertapp.ui.openmap.navigation.openMapScreen
import com.example.criminalalertapp.ui.report.navigation.reportScreen

@Composable
internal fun AppNavHost() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination != null && !currentDestination.hasRoute(SplashRoute::class)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0.dp)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = SplashRoute,
                modifier = Modifier.fillMaxSize()
            ) {
                splashScreen(
                    onTimeout = {
                        navController.navigate(HomeRoute) {
                            popUpTo(SplashRoute) { inclusive = true }
                        }
                    }
                )
                homeScreen()
                openMapScreen()
                reportScreen(
                    onBackClicked = {
                        navController.popBackStack()
                    }
                )
            }
            if (showBottomBar) {
                FloatingBottomBar(navController = navController)
            }
        }
    }
}