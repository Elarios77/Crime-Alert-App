package com.example.criminalalertapp.ui.home.screen

import com.example.criminalalertapp.R

data class HomeScreenCardItem(
    val image: Int,
    val text: Int,
    val subtext: Int
)

val homeScreenCardItemList = listOf(
    HomeScreenCardItem(
        image = R.drawable.alert_bell,
        text = R.string.hs_cardItem1,
        subtext = R.string.hs_cardItem1_text
    ),
    HomeScreenCardItem(
        image = R.drawable.map,
        text = R.string.hs_cardItem2,
        subtext = R.string.hs_cardItem2_text
    ),
    HomeScreenCardItem(
        image = R.drawable.handshake,
        text = R.string.hs_cardItem3,
        subtext = R.string.hs_cardItem3_title
    )
)