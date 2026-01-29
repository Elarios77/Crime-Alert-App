package com.example.criminalalertapp.ui.components.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun SpinEffect(
    isActive: Boolean,
    content: @Composable () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isActive) 360f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
    Box(modifier = Modifier.rotate(rotation)) {
        content()
    }
}