package com.example.criminalalertapp.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.criminalalertapp.R
import com.example.criminalalertapp.ui.components.animations.BottomUiItem
import com.example.criminalalertapp.ui.components.animations.BounceEffect
import com.example.criminalalertapp.ui.components.animations.IconAnimation
import com.example.criminalalertapp.ui.components.animations.ShakeEffect
import com.example.criminalalertapp.ui.components.animations.SpinEffect
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme

@Composable
fun NavBarItem(
    item: BottomUiItem,
    onClick: () -> Unit
) {
    val background by animateColorAsState(
        targetValue = if (item.isSelected) colorResource(R.color.GlassHighlight) else colorResource(R.color.GlassBlue)
    )

    val contentColor = if (item.isSelected) Color.White else colorResource(R.color.UnselectedWhite)

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        )
        {
            val iconContent = @Composable {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(35.dp)
                )
            }
            when (item.animationType) {
                IconAnimation.BOUNCE -> BounceEffect(isActive = item.isSelected, content = iconContent)
                IconAnimation.SHAKE -> ShakeEffect(isActive = item.isSelected, content = iconContent)
                IconAnimation.SPIN -> SpinEffect(isActive = item.isSelected, content = iconContent)
            }

            AnimatedVisibility(visible = item.isSelected) {
                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.title,
                        color = contentColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                }
            }
        }
    }
}

@Composable
fun FloatingBottomBar() {
    val items = listOf(
        BottomUiItem(
            title = stringResource(R.string.home),
            icon = Icons.Default.Home,
            isSelected = true,
            animationType = IconAnimation.BOUNCE,
        ),
        BottomUiItem(
            title = stringResource(R.string.map),
            icon = Icons.Default.Map,
            isSelected = false,
            animationType = IconAnimation.SHAKE
        ),
        BottomUiItem(
            title = stringResource(R.string.report),
            icon = Icons.Default.LocalPolice,
            isSelected = false,
            animationType = IconAnimation.SPIN,
        )
    )

    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Surface(
            modifier = Modifier
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(50.dp)),
            color = colorResource(R.color.PoliceDarkBlue)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    NavBarItem(
                        item = item.copy(isSelected = index == selectedIndex),
                        onClick = { selectedIndex = index }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FloatingBottomBarPreview() {
    FloatingBottomBar()
}

@Preview(showBackground = true)
@Composable
fun NavBarItemPreview() {
    CriminalAlertAppTheme {
        NavBarItem(
            item = BottomUiItem(
                title = "Home",
                icon = Icons.Default.Home,
                isSelected = true,
                animationType = IconAnimation.BOUNCE
            ),
            onClick = {}
        )
    }
}