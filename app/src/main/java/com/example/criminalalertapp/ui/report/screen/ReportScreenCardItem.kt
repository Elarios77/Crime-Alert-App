package com.example.criminalalertapp.ui.report.screen

import androidx.compose.ui.res.stringResource
import com.example.criminalalertapp.R

data class ReportScreenCardItem(
    val image: Int,
    val text: Int,
    val subtext: Int
)

val reportScreenCardItemList = listOf(
    ReportScreenCardItem(
        image = R.drawable.alert_bell,
        text = R.string.hs_cardItem1,
        subtext = R.string.hs_cardItem1_text
    ),
    ReportScreenCardItem(
        image = R.drawable.map,
        text = R.string.hs_cardItem2,
        subtext = R.string.hs_cardItem2_text
    ),
    ReportScreenCardItem(
        image = R.drawable.handshake,
        text = R.string.hs_cardItem3,
        subtext = R.string.hs_cardItem3_title
    )
)