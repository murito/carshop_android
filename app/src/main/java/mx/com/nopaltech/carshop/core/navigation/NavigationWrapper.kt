package mx.com.nopaltech.carshop.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import mx.com.nopaltech.carshop.CarDetailScreen
import mx.com.nopaltech.carshop.DealersScreen
import mx.com.nopaltech.carshop.MainScreen
import mx.com.nopaltech.carshop.data.database.model.CarViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val viewModel: CarViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Main){
        composable<Main>{
            MainScreen(viewModel = viewModel){
                carId -> navController.navigate(CarDetail(carId = carId) )
            }
        }

        composable<CarDetail>{ backStackEntry ->
            val carDetail = backStackEntry.toRoute<CarDetail>()
            CarDetailScreen(viewModel = viewModel, carId = carDetail.carId, navigateToDealers = {
                navController.navigate(Dealers)
            }){
                navController.navigate(Main){
                    popUpTo<Main>{ inclusive = true }
                }
            }
        }

        composable<Dealers>{
            DealersScreen(viewModel = viewModel){
                navController.navigate(Main){
                    popUpTo<Main>{ inclusive = true }
                }
            }
        }
    }
}