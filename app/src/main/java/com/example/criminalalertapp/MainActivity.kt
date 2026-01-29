package com.example.criminalalertapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.criminalalertapp.ui.report.screen.ReportScreen
import com.example.criminalalertapp.ui.animatedsplashscreen.SplashScreen
import com.example.criminalalertapp.ui.components.FloatingBottomBar
import com.example.criminalalertapp.ui.navigation.AppNavHost
import com.example.criminalalertapp.ui.openmap.screen.OpenMapScreen
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CriminalAlertAppTheme(darkTheme = false) {
                //TEMPORARY
                //AppNavHost()
                FloatingBottomBar()
            }
        }
    }
}