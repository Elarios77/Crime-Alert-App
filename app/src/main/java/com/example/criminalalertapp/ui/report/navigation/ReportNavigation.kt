package com.example.criminalalertapp.ui.report.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.example.criminalalertapp.ui.report.screen.ReportScreen
import com.example.criminalalertapp.ui.report.viewmodel.ReportViewModel
import com.example.criminalalertapp.util.navigation.NavigationRoute
import com.example.criminalalertapp.util.navigation.glanceFade
import kotlinx.serialization.Serializable

@Serializable
internal data object ReportRoute : NavigationRoute()

internal fun NavGraphBuilder.reportScreen(onBackClicked: () -> Boolean) {
    glanceFade<ReportRoute> {
        val viewModel: ReportViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ReportScreen(
            uiState = uiState,
            onStreetNameChange = viewModel::onStreetNameChange,
            onCategoryNameChange = viewModel::onCategoryNameChange,
            onMonthChange = viewModel::onMonthChange,
            submitCrime = {viewModel.submitCrime(51.5074, -0.1278)},
            onBackClicked = {onBackClicked()}
        )
    }
}