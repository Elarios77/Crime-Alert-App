package com.example.criminalalertapp.ui.report.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.usecase.ReportCrimeUseCase
import com.example.criminalalertapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportCrimeUseCase: ReportCrimeUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReportUiState())
    val uiState: StateFlow<ReportUiState> = _uiState.asStateFlow()

    fun onStreetNameChange(newStreetName: String) {
        _uiState.update {
            it.copy(
                isLoading = false,
                streetName = newStreetName,
                error = null
            )
        }
    }

    fun onCategoryNameChange(newCategory: String) {
        _uiState.update {
            it.copy(
                isLoading = false,
                category = newCategory,
                error = null
            )
        }
    }

    fun submitCrime(lat: Double, lng: Double) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            if (_uiState.value.streetName.isBlank()) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Street name cannot be empty"
                    )
                }
                return@launch
            }

            val newCrime = CrimeItem(
                id = 0,
                category = _uiState.value.category,
                month = _uiState.value.month,
                latitude = lat,
                longitude = lng,
                streetName = _uiState.value.streetName
            )

            when ( val result = reportCrimeUseCase(newCrime)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message ?: "Could not save crime") }

                }

                else -> {}
            }
        }
    }

    fun onMonthChange(newMonth: String) {
        _uiState.update {
            it.copy(
                isLoading = false,
                month = newMonth,
                error = null
            )
        }

    }

    fun resetState() {
        _uiState.value = ReportUiState()
    }
}
