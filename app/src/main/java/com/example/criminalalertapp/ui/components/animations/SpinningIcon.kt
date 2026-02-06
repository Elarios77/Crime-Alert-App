package com.example.criminalalertapp.ui.components.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun SpinningIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?
) {
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
    }
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.rotate(rotation.value),
        tint = Color.Unspecified
    )
}
