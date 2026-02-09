package com.example.criminalalertapp.ui.report.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.criminalalertapp.R
import com.example.criminalalertapp.ui.components.animations.SpinningIcon
import com.example.criminalalertapp.ui.components.datepicker.CustomDatePicker
import com.example.criminalalertapp.ui.report.viewmodel.ReportUiState
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    uiState: ReportUiState,
    onStreetNameChange: (String) -> Unit,
    onCategoryNameChange: (String) -> Unit,
    onMonthChange: (String) -> Unit,
    submitCrime: () -> Unit,
    onBackClicked: () -> Unit
) {

    val isFormValid = uiState.streetName.isNotBlank() &&
            uiState.category.isNotBlank() &&
            uiState.month.isNotBlank()

    ReportContent(
        streetName = uiState.streetName,
        category = uiState.category,
        month = uiState.month,
        isLoading = uiState.isLoading,
        isFormValid = isFormValid,
        onStreetNameChange = onStreetNameChange,
        onCategoryNameChange = onCategoryNameChange,
        onMonthChange = onMonthChange,
        submitCrime = submitCrime,
        onBackClicked = onBackClicked
    )
}

@Composable
fun ReportContent(
    streetName: String,
    category: String,
    month: String,
    isFormValid: Boolean,
    isLoading: Boolean,
    onMonthChange: (String) -> Unit,
    onCategoryNameChange: (String) -> Unit,
    onStreetNameChange: (String) -> Unit,
    submitCrime: () -> Unit,
    onBackClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.PoliceDarkBlue),
                        colorResource(R.color.PoliceGray)
                    )
                )
            )
    ) {
        TopBar(onBackClicked)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        )
        {
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = streetName,
                onValueChange = onStreetNameChange,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                label = {
                    Text(
                        stringResource(R.string.location_street),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                placeholder = { Text("e.g. Baker Street", color = Color.White) },
                leadingIcon = {
                    Icon(
                        Icons.Default.LocationOn,
                        null,
                        tint = colorResource(R.color.PoliceDarkBlue)
                    )
                },
                singleLine = true,
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.PoliceDarkBlue),
                    unfocusedBorderColor = colorResource(R.color.PoliceDarkBlue)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CrimeCategoryDropDownMenu(
                selectedCategory = category,
                onCategorySelected = onCategoryNameChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomDatePicker(
                selectedDate = month,
                onDateSelected = { newDate -> onMonthChange(newDate) }
            )

            Spacer(modifier = Modifier.weight(1f))

            ElevatedButton(
                onClick = { submitCrime() },
                modifier = Modifier.fillMaxWidth(),
                colors = if (isFormValid) ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        R.color.PoliceRed
                    )
                ) else
                    ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.PoliceRed).copy(
                            alpha = 0.2f
                        )
                    ),
                enabled = isFormValid
            )
            {
                Text(
                    text = stringResource(R.string.reportBtn),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun TopBar(onBackClicked:()-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = colorResource(R.color.PoliceDarkBlue),
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
            ),
        contentAlignment = Alignment.Center
    )
    {
        IconButton(onClick = {onBackClicked()})
        {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.TopStart)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SpinningIcon(
                painter = painterResource(R.drawable.police_badge),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.reportScreen_TitleLarge),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(
                text = stringResource(R.string.reportScreen_subtitle),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrimeCategoryDropDownMenu(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf(
        "Anti-social behavior",
        "Burglary",
        "Violent crime",
        "Robbery",
        "Vehicle crime",
        "Other theft"
    )
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedCategory,
            onValueChange = { },
            label = {
                Text(
                    stringResource(R.string.category),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true),
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.siren),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.PoliceDarkBlue),
                unfocusedBorderColor = colorResource(R.color.PoliceDarkBlue),

                ),
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(color = colorResource(R.color.PoliceDarkBlue))
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category, color = Color.White) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {
    CriminalAlertAppTheme {
        ReportContent(
            streetName = "10,Str.",
            category = "Robbery",
            month = "2026",
            isFormValid = true,
            isLoading = true,
            onMonthChange = {},
            onCategoryNameChange = {},
            onStreetNameChange = { },
            submitCrime = { },
            onBackClicked = {}
        )
    }
}

