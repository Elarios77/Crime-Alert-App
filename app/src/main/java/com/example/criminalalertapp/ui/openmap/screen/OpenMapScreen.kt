package com.example.criminalalertapp.ui.openmap.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.criminalalertapp.R
import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.ui.openmap.viewModel.OpenMapUiState
import com.example.criminalalertapp.ui.openmap.viewModel.OpenMapViewModel
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun OpenMapScreen(
    viewModel: OpenMapViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error)
    {
        uiState.error?.let { errorMsg ->
            snackBarHostState.showSnackbar(message = errorMsg)
        }
    }

    MapContent(
        state = uiState,
        snackBarHostState = snackBarHostState
    )
}

@Composable
fun MapContent(
    state: OpenMapUiState,
    snackBarHostState: SnackbarHostState
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OsmMapView(
                crimes = state.crimes,
                modifier = Modifier.fillMaxSize()
            )

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun OsmMapView(
    crimes: List<CrimeItem>, modifier: Modifier
) {
    val isPreview = LocalInspectionMode.current
    if (isPreview) {
        Box(
            modifier = modifier.background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.OsmMapViewText),
                color = Color.Black
            )
        }
    } else {
        AndroidView(
            modifier = modifier,
            factory = { context ->
                MapView(context).apply {
                    setMultiTouchControls(true)
                    val london = GeoPoint(51.5074, -0.1278)
                    controller.setZoom(13.0)
                    controller.setCenter(london)
                    val londonBounds = BoundingBox(51.70, 0.30, 51.30, -0.50)
                    setScrollableAreaLimitDouble(londonBounds)
                    setMinZoomLevel(10.0)
                    setMaxZoomLevel(18.0)
                }
            },
            update = { mapView ->
                mapView.overlays.clear()
                crimes.forEach { crime ->
                    val marker = Marker(mapView)
                    marker.position = GeoPoint(crime.latitude, crime.longitude)
                    marker.title = "${crime.category} (${crime.month})"
                    marker.snippet = crime.streetName
                    marker.icon = ContextCompat.getDrawable(mapView.context, R.drawable.ic_pin_red)
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    marker.setOnMarkerClickListener { m, _ ->
                        m.showInfoWindow()
                        true
                    }
                    mapView.overlays.add(marker)
                }
                mapView.invalidate()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    CriminalAlertAppTheme {

        val mockCrime = listOf(
            CrimeItem(
                id = 1,
                category = "Burglary",
                month = "2024-01",
                latitude = 51.5074,
                longitude = -0.1278,
                streetName = "Baker Street"
            )
        )

        MapContent(
            state = OpenMapUiState(
                isLoading = false,
                crimes = mockCrime,
                error = null
            ),
            snackBarHostState = remember { SnackbarHostState() }
        )
    }
}
