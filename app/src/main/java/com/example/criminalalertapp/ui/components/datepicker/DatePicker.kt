package com.example.criminalalertapp.ui.components.datepicker

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.criminalalertapp.R
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme
import com.example.criminalalertapp.util.toFormattedDateString
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(modifier: Modifier = Modifier) {
    var isDatePickerOpen by remember { mutableStateOf(false) }

    val state = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = PastOrPresentSelectableDates
    )

    if (isDatePickerOpen) {
        DatePickerDialog(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            onDismissRequest = { isDatePickerOpen = false },
            confirmButton = {
                TextButton(onClick = { isDatePickerOpen = false }) {
                    Text(stringResource(R.string.dp_select))
                }
            },
            dismissButton = {
                TextButton(onClick = { isDatePickerOpen = false }) {
                    Text(stringResource(R.string.dp_cancel))
                }
            },
            content = {
                DatePicker(
                    state = state,
                    title = {
                        Text(
                            text = stringResource(R.string.dp_select),
                            modifier = Modifier.padding(16.dp)
                        )
                    },
                )
            }
        )
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { isDatePickerOpen = true }) {
            Text(stringResource(R.string.dp_chooseDate))
        }
        Text("Selected: ${state.selectedDateMillis.toFormattedDateString()}")
    }
}

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
private object PastOrPresentSelectableDates : SelectableDates {
    private val today = LocalDate.now()

    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val date = Instant.ofEpochMilli(utcTimeMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return !date.isAfter(today)
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year <= today.year
    }
}

@Preview(showBackground = true)
@Composable
fun CustomDatePickerPreview() {
    CriminalAlertAppTheme {
        CustomDatePicker()
    }
}