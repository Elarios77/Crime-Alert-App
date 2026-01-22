package com.example.criminalalertapp.ui.openmap.viewModel

import com.example.criminalalertapp.domain.model.CrimeItem

data class OpenMapUiState(
    val isLoading: Boolean = false,
    val crimes: List<CrimeItem> = emptyList(),
    val error: String? = null
)