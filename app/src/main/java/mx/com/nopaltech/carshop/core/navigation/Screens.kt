package mx.com.nopaltech.carshop.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Main

@Serializable
data class CarDetail(val carId: Int)

@Serializable
object Dealers