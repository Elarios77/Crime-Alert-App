package com.example.criminalalertapp.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long?.toFormattedDateString():String{
    val timeMillis = this?:System.currentTimeMillis()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy",Locale.getDefault())
    return Instant.ofEpochMilli(timeMillis)
        .atZone((ZoneId.systemDefault()))
        .format(formatter)
}
