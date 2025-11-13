package mx.com.nopaltech.carshop.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.com.nopaltech.carshop.data.database.CarDatabase
import mx.com.nopaltech.carshop.data.database.dao.CarDao
import mx.com.nopaltech.carshop.data.database.dao.DealerDao
import mx.com.nopaltech.carshop.data.database.entities.CarEntity
import mx.com.nopaltech.carshop.data.database.entities.DealerEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CarDatabase{
        return Room.databaseBuilder(
            context,
            CarDatabase::class.java,
            "car_shop_database"
        )
            .addCallback(object: RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        val database = Room.databaseBuilder(
                            context,
                            CarDatabase::class.java,
                            "car_shop_database"
                        ).build()
                        prepopulateDatabase(database)
                    }
                }
            })
            .build()
    }

    @Provides
    fun provideCarDao(db: CarDatabase): CarDao = db.getCarDao()

    @Provides
    fun provideDealerDao(db: CarDatabase): DealerDao = db.getDealerDao()

    private suspend fun prepopulateDatabase(database: CarDatabase) {
        val carsDao = database.getCarDao()
        val dealersDao = database.getDealerDao()

        val sampleDealers = listOf(
            DealerEntity( name = "Grupo Plascencia", latitude=  20.6437563, longitude= -103.4117188),
            DealerEntity(name = "Toyota Country", latitude= 20.69845, longitude= -103.3714713),
            DealerEntity( name = "Toyota Acueducto", latitude= 20.7233263, longitude= -103.4197064),
            DealerEntity(name = "Naosa Chevrolet", latitude= 20.6586546, longitude= -103.3889186),
            DealerEntity(name = "Mazda Gonzalez Gallo", latitude= 20.648025, longitude= -103.3373061),
            DealerEntity( name = "Kia Lopez Mateos", latitude= 20.642578, longitude= -103.410581)
        )

        val sampleCars = listOf(
            CarEntity(make="Toyota", name="Corolla", year=2015, price=210000.00, mileage=85000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image="corola_2015"),
            CarEntity(make="Honda", name="Civic", year=2017, price=265000.00, mileage=64000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image="civic_2017"),
            CarEntity(make="Nissan", name="Versa", year=2020, price=295000.00, mileage=38000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image="versa_2020"),
            CarEntity(make="Ford", name="Focus", year=2014, price=185000.00, mileage=97000.00, engineType="Gasolina", warranty="2 años o 50,000 km", image="focus_2014"),
            CarEntity(make="Chevrolet", name="Aveo", year=2018, price=215000.00, mileage=52000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image="aveo_2018"),
            CarEntity(make="Kia", name="Rio", year=2021, price=315000.00, mileage=22000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image="rio_2021"),
            CarEntity(make="Hyundai", name="Elantra", year=2022, price=385000.00, mileage=15000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image="elantra_2022"),
            CarEntity(make="Mazda", name="3", year=2023, price=410000.00, mileage=12000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image="mazda3_2023"),
            CarEntity(make="Tesla", name="Model 3", year=2024, price=950000.00, mileage=8000.00, engineType="Eléctrico", warranty="4 años o 80,000 km", image="model3_2024"),
            CarEntity(make="BMW", name="320i", year=2019, price=680000.00, mileage=40000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image="c_320i_2019"),
            CarEntity(make="Audi", name="A3", year=2020, price=720000.00, mileage=35000.00, engineType="Gasolina", warranty="4 años o 80,000 km", image="a3_2020"),
            CarEntity(make="Toyota", name="Camry", year=2022, price=590000.00, mileage=21000.00, engineType="Híbrido", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Honda", name="CR-V", year=2021, price=650000.00, mileage=28000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Nissan", name="Sentra", year=2018, price=255000.00, mileage=58000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Ford", name="Escape", year=2019, price=480000.00, mileage=43000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Chevrolet", name="Tracker", year=2021, price=475000.00, mileage=24000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Kia", name="Sportage", year=2020, price=530000.00, mileage=32000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Hyundai", name="Tucson", year=2023, price=590000.00, mileage=10000.00, engineType="Híbrido", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Mazda", name="CX-5", year=2024, price=610000.00, mileage=8000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Tesla", name="Model Y", year=2023, price=1100000.00, mileage=6000.00, engineType="Eléctrico", warranty="4 años o 80,000 km", image=""),
            CarEntity(make="BMW", name="X1", year=2022, price=890000.00, mileage=12000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Audi", name="Q3", year=2021, price=870000.00, mileage=18000.00, engineType="Gasolina", warranty="4 años o 80,000 km", image=""),
            CarEntity(make="Toyota", name="Hilux", year=2020, price=520000.00, mileage=45000.00, engineType="Diésel", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Honda", name="HR-V", year=2022, price=540000.00, mileage=16000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Nissan", name="Altima", year=2021, price=465000.00, mileage=30000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Ford", name="Ranger", year=2020, price=560000.00, mileage=39000.00, engineType="Diésel", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Chevrolet", name="Onix", year=2023, price=365000.00, mileage=8000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Kia", name="Forte", year=2022, price=385000.00, mileage=12000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Hyundai", name="Accent", year=2020, price=315000.00, mileage=27000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Mazda", name="CX-30", year=2021, price=465000.00, mileage=19000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Tesla", name="Model S", year=2020, price=1800000.00, mileage=25000.00, engineType="Eléctrico", warranty="4 años o 80,000 km", image=""),
            CarEntity(make="BMW", name="i3", year=2019, price=720000.00, mileage=30000.00, engineType="Eléctrico", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Audi", name="e-tron", year=2021, price=1500000.00, mileage=18000.00, engineType="Eléctrico", warranty="4 años o 80,000 km", image=""),
            CarEntity(make="Toyota", name="Prius", year=2019, price=460000.00, mileage=40000.00, engineType="Híbrido", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Honda", name="Accord", year=2020, price=530000.00, mileage=28000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Nissan", name="Kicks", year=2022, price=395000.00, mileage=15000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Ford", name="Mustang", year=2023, price=890000.00, mileage=9000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Chevrolet", name="Camaro", year=2021, price=950000.00, mileage=14000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Kia", name="Soul", year=2019, price=345000.00, mileage=47000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Hyundai", name="Santa Fe", year=2020, price=520000.00, mileage=31000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Mazda",name="2", year=2020, price=310000.00, mileage=33000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Tesla", name="Model X", year=2022, price=2200000.00, mileage=12000.00, engineType="Eléctrico", warranty="4 años o 80,000 km", image=""),
            CarEntity(make="BMW", name="X3", year=2023, price=1100000.00, mileage=9000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Audi", name="A4", year=2018, price=580000.00, mileage=50000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Toyota", name="Yaris", year=2021, price=325000.00, mileage=21000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image=""),
            CarEntity(make="Honda", name="Fit", year=2019, price=285000.00, mileage=38000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Nissan", name="March", year=2018, price=220000.00, mileage=59000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Ford", name="Explorer", year=2020, price=740000.00, mileage=27000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Chevrolet", name="Spark", year=2021, price=260000.00, mileage=25000.00, engineType="Gasolina", warranty="3 años o 60,000 km", image=""),
            CarEntity(make="Mazda", name="MX-5", year=2024, price=680000.00, mileage=6000.00, engineType="Gasolina", warranty="5 años o 100,000 km", image="")
        )

        carsDao.insertAll(sampleCars)
        dealersDao.insertAll(sampleDealers)
    }
}