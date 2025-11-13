package mx.com.nopaltech.carshop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import mx.com.nopaltech.carshop.data.database.model.CarViewModel

@Composable
fun DealersScreen(viewModel: CarViewModel, navigateToMain: () -> Unit){
    val dealers by viewModel.dealers.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}