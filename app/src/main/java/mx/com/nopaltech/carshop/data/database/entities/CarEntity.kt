package mx.com.nopaltech.carshop.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "make") val make: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "mileage") val mileage: Double,
    @ColumnInfo(name = "engineType") val engineType: String,
    @ColumnInfo(name = "warranty") val warranty: String,
    @ColumnInfo(name = "image") val image: String
)
