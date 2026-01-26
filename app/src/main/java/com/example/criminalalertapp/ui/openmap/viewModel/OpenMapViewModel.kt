package com.example.criminalalertapp.ui.openmap.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criminalalertapp.usecase.GetCrimesUseCase
import com.example.criminalalertapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    private var searchJob: Job?=null

    init {
        loadCrimes(51.5074,-0.1278)
    }
     fun loadCrimes(lat: Double,lng: Double) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(800)
            _uiState.update { it.copy(isLoading = true) }
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