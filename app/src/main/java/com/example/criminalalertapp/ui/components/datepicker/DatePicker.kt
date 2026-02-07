package com.example.criminalalertapp.ui.components.datepicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.criminalalertapp.R
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme
import com.example.criminalalertapp.util.toFormattedDateString
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    modifier: Modifier = Modifier,
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
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
                TextButton(onClick = {
                    state.selectedDateMillis?.let { millis ->
                        onDateSelected(millis.toFormattedDateString())
                    }
                    isDatePickerOpen = false
                }
                ) {
                    Text(
                        stringResource(R.string.dp_select),
                        color = colorResource(R.color.PoliceDarkBlue)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { isDatePickerOpen = false }) {
                    Text(
                        stringResource(R.string.dp_cancel),
                        color = colorResource(R.color.PoliceDarkBlue)
                    )
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
    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            ),
            label = {
                Text(
                    stringResource(R.string.date),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            placeholder = { Text("DD/MM/YYYY", color = Color.White) },
            leadingIcon = {
                Icon(
                    Icons.Default.DateRange,
                    null,
                    tint = colorResource(R.color.PoliceDarkBlue)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.PoliceDarkBlue),
                unfocusedBorderColor = colorResource(R.color.PoliceDarkBlue)
            )
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { isDatePickerOpen = true })
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
        CustomDatePicker(
            selectedDate = "04/02/2025",
            onDateSelected = { }
        )
    }
}