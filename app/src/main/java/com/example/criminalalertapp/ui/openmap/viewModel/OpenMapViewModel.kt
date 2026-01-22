package com.example.criminalalertapp.ui.openmap.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criminalalertapp.usecase.GetCrimesUseCase
import com.example.criminalalertapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenMapViewModel @Inject constructor(
    private val getCrimesUseCase: GetCrimesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(OpenMapUiState())
    val uiState: StateFlow<OpenMapUiState> = _uiState.asStateFlow()

    init {
        loadCrimes()
    }

    private fun loadCrimes() {
        viewModelScope.launch {
            val lat = 51.5074
            val lng = -0.1278

            getCrimesUseCase(null, lat, lng).collect{ result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = result.isLoading) }
                    }

                    is Resource.Success -> {
                        _uiState.update { it.copy(crimes = result.data ?: emptyList(), isLoading = false, error = null) }
                    }

                    is Resource.Error -> {
                        _uiState.update { it.copy(error = result.message ?: "Unknown error", isLoading = false) }
                    }
                }
            }
        }
    }
}