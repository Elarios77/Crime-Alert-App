package com.example.criminalalertapp.ui.openmap.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.criminalalertapp.ui.openmap.screen.OpenMapScreen
import com.example.criminalalertapp.ui.openmap.viewModel.OpenMapViewModel
import kotlinx.serialization.Serializable

@Serializable
object OpenMapRoute

internal fun NavGraphBuilder.openMapScreen() {
    composable<OpenMapRoute> {

        val viewModel: OpenMapViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        OpenMapScreen(
            uiState = uiState,
            onCameraMove = { lat, lng -> viewModel.loadCrimes(lat, lng) },
        )
    }
}