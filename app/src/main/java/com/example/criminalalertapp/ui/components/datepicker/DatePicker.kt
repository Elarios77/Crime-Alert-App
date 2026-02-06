package com.example.criminalalertapp.ui.components.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(modifier: Modifier = Modifier){
    val state = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        DatePicker(
            state = state,
            showModeToggle = false)
        Text("Selected: ${state.selectedDateMillis}")
    }
}


@Preview(showBackground = true)
@Composable
fun CustomDatePickerPreview(){
    CriminalAlertAppTheme {
        CustomDatePicker()
    }
}