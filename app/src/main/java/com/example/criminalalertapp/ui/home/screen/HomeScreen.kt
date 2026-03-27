package com.example.criminalalertapp.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.criminalalertapp.R
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
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
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = stringResource(R.string.network),
                    color = colorResource(R.color.PoliceDarkBlue),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = stringResource(R.string.hs_subtitle),
                    color = colorResource(R.color.PoliceDarkBlue),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        HorizontalDivider(color = Color.White)
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(items = homeScreenCardItemList, itemContent = { item ->
                Card(
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .height(120.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = cardElevation(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(item.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(24.dp)

                        )
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = stringResource(item.text),
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W500
                            )
                            Text(
                                text = stringResource(item.subtext),
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                    }
                }
            }
            )
            item {
                Text(
                    text = stringResource(R.string.hs_message),
                    modifier = Modifier.padding(top = 8.dp),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.hs_slogan),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = Color.Blue.copy(alpha = 0.8f),
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic
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
