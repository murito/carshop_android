package mx.com.nopaltech.carshop

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.com.nopaltech.carshop.data.database.entities.CarEntity
import mx.com.nopaltech.carshop.data.database.model.CarViewModel
import mx.com.nopaltech.carshop.utils.Utils

@Composable
fun MainScreen(viewModel: CarViewModel, navigateToDetail: (Int) -> Unit){
    val cars by viewModel.cars.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = Color.Black)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Car Shop",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(vertical = 30.dp, horizontal = 15.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Header Icon",
                    tint = Color.White
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                cars.forEach { carEntity ->
                    item{
                        CarItem(carEntity) {
                            navigateToDetail(carEntity.id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CarItem(car: CarEntity, onClick: () -> Unit){
    Row(
        modifier = Modifier.fillMaxSize()
            .clickable(onClick = {
                onClick()
            })
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .width(150.dp)
                .background(Color.Black, shape = RoundedCornerShape(10.dp))
                .padding(vertical = 10.dp)
        ){
            val context = LocalContext.current
            val resources = context.resources
            val packageName = context.packageName
            val drawableId = resources.getIdentifier(car.image, "drawable", packageName)

            if( drawableId > 0 ){
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = "car image",
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                )
            }else {
                Spacer(Modifier.padding(vertical = 10.dp))
            }


            Text(
                text = car.year.toString(),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.6f))
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            Row() {
                Text(
                    text = "${car.make} ${car.name}",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.padding(vertical = 10.dp))

            Text(
                text = "${Utils.currencyFormat(car.price)} MXN",
                color = Color.White,
                fontSize = 17.sp
            )
        }
    }
}

