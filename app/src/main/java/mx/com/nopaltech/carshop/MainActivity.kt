package mx.com.nopaltech.carshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import mx.com.nopaltech.carshop.core.navigation.NavigationWrapper
import mx.com.nopaltech.carshop.ui.theme.CarShopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarShopTheme{
                NavigationWrapper()
            }
        }
    }
}
