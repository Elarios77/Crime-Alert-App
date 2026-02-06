package com.example.criminalalertapp.ui.report.viewmodel

import com.example.criminalalertapp.util.toFormattedDateString

data class ReportUiState(
    val isLoading: Boolean = false,
    val streetName: String = "",
    val category: String = "",
    val month: String = System.currentTimeMillis().toFormattedDateString(),
    val isSuccess: Boolean = false,
    val error: String? = null
)