package mx.com.nopaltech.carshop.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mx.com.nopaltech.carshop.data.database.entities.CarEntity

@Dao
interface CarDao{
    @Query(value = "SELECT * FROM CARS_TABLE")
    fun getAllCars(): Flow<List<CarEntity>>

    @Query(value = "SELECT * FROM CARS_TABLE WHERE id = :id")
    fun getCarById(id: Int): Flow<CarEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cars: List<CarEntity>)
}