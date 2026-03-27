package com.example.criminalalertapp.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.criminalalertapp.R
import com.example.criminalalertapp.ui.report.screen.reportScreenCardItemList
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to colorResource(R.color.light_blue),
                        0.7f to Color.White
                    )
                )
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.police_station),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(130.dp)
                )
                Text(
                    text = stringResource(R.string.crime_alert),
                    color = colorResource(R.color.PoliceDarkBlue),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp
                )
                Text(
                    text = stringResource(R.string.network),
                    color = colorResource(R.color.PoliceDarkBlue),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp
                )
                Text(
                    text = stringResource(R.string.hs_subtitle),
                    color = colorResource(R.color.PoliceDarkBlue),
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CriminalAlertAppTheme {
        HomeScreen()
    }
}
