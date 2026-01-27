package com.example.criminalalertapp.ui.report.viewmodel

data class ReportUiState(
    val isLoading: Boolean = false,
    val streetName: String = "",
    val category: String = "",
    val month: String = "",
    val isSuccess: Boolean = false,
    val error: String? = null
)