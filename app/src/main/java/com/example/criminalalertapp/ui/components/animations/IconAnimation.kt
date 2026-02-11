package com.example.criminalalertapp.ui.components.animations

import androidx.compose.ui.graphics.vector.ImageVector

enum class IconAnimation {
    BOUNCE,
    SPIN,
    SHAKE
}

data class BottomUiItem(
    val title: String,
    val icon: ImageVector,
    val isSelected: Boolean,
    val animationType: IconAnimation,
    val route: Any
)