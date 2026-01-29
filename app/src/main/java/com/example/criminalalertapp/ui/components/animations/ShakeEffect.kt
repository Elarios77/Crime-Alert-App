package com.example.criminalalertapp.ui.components.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun ShakeEffect(
    isActive: Boolean,
    content: @Composable () -> Unit
) {
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(isActive)
    {
        if (isActive) {
            rotation.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 500
                    0.0f at 0
                    20.0f at 100
                    -20.0f at 200
                    10.0f at 300
                    -10.0f at 400
                    0.0f at 500
                }
            )
        }
    }
    Box(modifier = Modifier.rotate(rotation.value)) {
        content()
    }

}