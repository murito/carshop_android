package mx.com.nopaltech.carshop.data.database.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import mx.com.nopaltech.carshop.data.database.dao.CarDao
import mx.com.nopaltech.carshop.data.database.dao.DealerDao
import mx.com.nopaltech.carshop.data.database.entities.CarEntity
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor (
    private val carDao: CarDao,
    private val dealerDao: DealerDao
) : ViewModel(){
    val cars = carDao.getAllCars().stateIn(
        scope =viewModelScope,
        started=SharingStarted.WhileSubscribed(5000),
        initialValue=emptyList()
    )

    fun getCarById(id: Int): Flow<CarEntity> = carDao.getCarById(id)

    val dealers = dealerDao.getAllDealers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}