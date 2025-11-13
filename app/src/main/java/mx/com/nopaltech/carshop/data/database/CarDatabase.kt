package mx.com.nopaltech.carshop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.com.nopaltech.carshop.data.database.dao.CarDao
import mx.com.nopaltech.carshop.data.database.dao.DealerDao
import mx.com.nopaltech.carshop.data.database.entities.CarEntity
import mx.com.nopaltech.carshop.data.database.entities.DealerEntity

@Database(entities =[CarEntity::class, DealerEntity::class], version = 1)
abstract class CarDatabase: RoomDatabase() {
    abstract fun getCarDao(): CarDao
    abstract fun getDealerDao(): DealerDao
}