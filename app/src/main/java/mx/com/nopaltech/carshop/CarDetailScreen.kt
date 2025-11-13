package mx.com.nopaltech.carshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.com.nopaltech.carshop.data.database.model.CarViewModel
import mx.com.nopaltech.carshop.utils.Utils


@Composable
fun CarDetailScreen(viewModel: CarViewModel, carId: Int, navigateToDealers: () -> Unit, navigateToMain: () -> Unit){
    val car by viewModel.getCarById(carId).collectAsStateWithLifecycle(initialValue = null)

    if ( car != null ){
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(color = Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                car?.let {
                    CarDetailView(
                        image = car!!.image,
                        make = car!!.make,
                        name = car!!.name,
                        year = car!!.year,
                        price = car!!.price,
                        mileage = car!!.mileage,
                        engineType = car!!.engineType,
                        warranty = car!!.warranty,
                        toHome = {
                            navigateToMain()
                        },
                        toDealers = {
                            navigateToDealers()
                        }
                    )
                } ?:
                    Text(text = "Cargando...")
            }
        }
    }

}

@Composable
fun CarDetailView(
    image: String,
    make: String,
    name: String,
    year: Int,
    price: Double,
    mileage: Double,
    engineType: String,
    warranty: String,
    toHome: () -> Unit,
    toDealers: () -> Unit
){
    val context = LocalContext.current
    val resources = context.resources
    val packageName = context.packageName
    val drawableId = resources.getIdentifier(image, "drawable", packageName)

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "car image",
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        )

        Text(
            text = "${make} ${name} ${year}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        Text(
            text = "${Utils.currencyFormat(data = price)} MXN",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        CarDetailRow(label ="Kilometraje: ", value = Utils.thousandsFormat(data = mileage))
        CarDetailRow(label ="Tipo de motor: ", value = engineType)
        CarDetailRow(label ="Garantía: ", value = warranty)

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Button(onClick = {
                toDealers()
            }){
                Text("Ver sucursales")
            }

            Button(onClick = {
                toHome()
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
}

@Composable
fun CarDetailRow(label: String, value: String){
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 40.dp)
    ){
        Text(
            text = label,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Text(
            text = value,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)
        )
    }
}

@Preview
@Composable
fun DetailPreview(){
    CarDetailView("a3_2020", "Audi", "A3", 2020,700000.00, 80000.00, "Gasolina", "3 años u 80 mil km", toHome = {}, toDealers = {})
}