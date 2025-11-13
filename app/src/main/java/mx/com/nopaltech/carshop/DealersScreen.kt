package mx.com.nopaltech.carshop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import mx.com.nopaltech.carshop.data.database.model.CarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealersScreen(viewModel: CarViewModel, navigateToMain: () -> Unit){
    val dealers by viewModel.dealers.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val gdl = LatLng(20.6613833, -103.3928058)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(gdl, 10f)
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    dealers.forEach { dealer ->
                        Marker(
                            state = MarkerState(position = LatLng(dealer.latitude, dealer.longitude)),
                            title = dealer.name
                        )
                    }
                }

                Row() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Button(onClick = {
                            showBottomSheet = true
                        }){
                            Text("Ver sucursales")
                        }

                        Button(onClick = {
                            navigateToMain()
                        }){
                            Row(){
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Header Icon",
                                    tint = Color.White,
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                )
                                Text("Home")
                            }
                        }
                    }
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState
                    ) {
                        // Sheet content
                        LazyColumn(
                            Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(vertical = 20.dp)
                        ) {
                            dealers.forEach { dealer ->
                                item {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable(onClick = {
                                                coroutineScope.launch {
                                                    cameraPositionState.animate(
                                                        update = CameraUpdateFactory.newLatLngZoom(
                                                            LatLng(dealer.latitude, dealer.longitude),
                                                            15f
                                                        ),
                                                        durationMs = 1000
                                                    )
                                                }

                                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                                    if (!sheetState.isVisible) {
                                                        showBottomSheet = false
                                                    }
                                                }
                                            })
                                    ) {
                                        Text(
                                            text = dealer.name,
                                            modifier = Modifier
                                                .padding(vertical = 10.dp, horizontal = 20.dp)
                                                .weight(1f)
                                        )

                                        Icon(
                                            imageVector = Icons.Filled.LocationOn,
                                            contentDescription = "Header Icon",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .padding(horizontal = 20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}