package com.example.criminalalertapp.ui.report.navigation

import androidx.compose.runtime.getValue

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.criminalalertapp.ui.report.screen.ReportScreen
import com.example.criminalalertapp.ui.report.viewmodel.ReportViewModel
import kotlinx.serialization.Serializable

@Serializable
object ReportRoute

fun NavGraphBuilder.reportScreen(onBackClicked: () -> Unit) {
    composable<ReportRoute> {

        val viewModel: ReportViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        ReportScreen(
            uiState = uiState,
            onStreetNameChange = viewModel::onStreetNameChange,
            onCategoryNameChange = viewModel::onCategoryNameChange,
            onMonthChange = viewModel::onMonthChange,
            submitCrime = { viewModel.submitCrime(51.5074, -0.1278) },
            onBackClicked = onBackClicked
        )
    }
}