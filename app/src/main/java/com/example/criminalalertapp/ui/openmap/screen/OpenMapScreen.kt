package com.example.criminalalertapp.ui.openmap.screen

import android.view.View
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.criminalalertapp.R
import com.example.criminalalertapp.domain.model.CrimeItem
import com.example.criminalalertapp.ui.openmap.viewModel.OpenMapUiState
import com.example.criminalalertapp.ui.theme.CriminalAlertAppTheme
import com.example.criminalalertapp.util.toBitMap
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.events.DelayedMapListener
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun OpenMapScreen(
    uiState:OpenMapUiState,
    onCameraMove: (Double, Double) -> Unit,
    onReportClicked: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error)
    {
        uiState.error?.let { errorMsg ->
            snackBarHostState.showSnackbar(message = errorMsg)
        }
    }

    MapContent(
        state = uiState,
        snackBarHostState = snackBarHostState,
        onCameraMove = onCameraMove
    )
}

@Composable
fun MapContent(
    state: OpenMapUiState,
    snackBarHostState: SnackbarHostState,
    onCameraMove: (Double, Double) -> Unit
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
                modifier = Modifier.fillMaxSize(),
                onCameraMove = onCameraMove
            )

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun OsmMapView(
    crimes: List<CrimeItem>,
    onCameraMove: (Double, Double) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val clusterIconDrawable = ContextCompat.getDrawable(context, R.drawable.cluser_icon)
    val clusterBitMap = clusterIconDrawable?.toBitMap()
    val pinIcon = ContextCompat.getDrawable(context, R.drawable.ic_pin_red)

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
                    setLayerType(View.LAYER_TYPE_HARDWARE, null)
                    controller.setZoom(14.0)
                    controller.setCenter(GeoPoint(51.5074, -0.1278))

                    val londonBounds = BoundingBox(51.7, 0.3, 51.3, -0.5)
                    setScrollableAreaLimitDouble(londonBounds)
                    minZoomLevel = 10.0
                    maxZoomLevel = 20.0

                    val mapListener = object : MapListener {
                        override fun onScroll(event: ScrollEvent?): Boolean {
                            event?.source?.let { map ->
                                val center = map.mapCenter
                                onCameraMove(center.latitude, center.longitude)
                            }
                            return true
                        }

                        override fun onZoom(event: ZoomEvent?): Boolean {
                            event?.source?.let { map ->
                                val center = map.mapCenter
                                onCameraMove(center.latitude, center.longitude)
                            }
                            return true
                        }
                    }
                    addMapListener(DelayedMapListener(mapListener, 500))
                }
            },
            update = { mapView ->
                val existingClusterer = mapView.overlays.firstOrNull { it is RadiusMarkerClusterer } as? RadiusMarkerClusterer

                val clusterer = if(existingClusterer != null){
                    existingClusterer
                }else{
                    RadiusMarkerClusterer(mapView.context).apply {
                        setIcon(clusterBitMap)
                        textPaint.color = Color.White.toArgb()
                        textPaint.textSize = 40f
                        mapView.overlays.add(0, this)
                    }
                }

                clusterer.items.clear()

                val markers = crimes.map {crime ->
                    Marker(mapView).apply {
                        position = GeoPoint(crime.latitude, crime.longitude)
                        title = "${crime.category} (${crime.month})"
                        snippet = crime.streetName
                        icon = pinIcon
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    }
                }
                clusterer.items.addAll(markers)
                clusterer.invalidate()
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
            snackBarHostState = remember { SnackbarHostState() },
            onCameraMove = { _, _ -> }
        )
    }
}
