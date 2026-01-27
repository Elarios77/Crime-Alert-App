package com.example.criminalalertapp.ui.openmap.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.example.criminalalertapp.ui.openmap.screen.OpenMapScreen
import com.example.criminalalertapp.ui.openmap.viewModel.OpenMapViewModel
import com.example.criminalalertapp.util.navigation.NavigationRoute
import com.example.criminalalertapp.util.navigation.glanceFade
import kotlinx.serialization.Serializable

@Serializable
internal data object OpenMapRoute : NavigationRoute()

internal fun NavGraphBuilder.openMapScreen(
    onNavigateToReport: () -> Unit
) {
    glanceFade<OpenMapRoute> {
        val viewModel: OpenMapViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        OpenMapScreen(
            uiState = uiState,
            onCameraMove = { lat, lng -> viewModel.loadCrimes(lat,lng) },
            onReportClicked = onNavigateToReport
        )
    }
}