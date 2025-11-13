package mx.com.nopaltech.carshop.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mx.com.nopaltech.carshop.data.database.entities.CarEntity
import mx.com.nopaltech.carshop.data.database.entities.DealerEntity

@Dao
interface DealerDao {
    @Query(value = "SELECT * FROM DEALERS_TABLE")
    fun getAllDealers(): Flow<List<DealerEntity>>

    @Query(value = "SELECT * FROM DEALERS_TABLE WHERE id = :id")
    fun getDealerById(id: Int): Flow<DealerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dealers: List<DealerEntity>)
}