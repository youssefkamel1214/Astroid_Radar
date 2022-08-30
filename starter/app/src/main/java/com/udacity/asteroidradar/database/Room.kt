package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
//this database dao used to update database show availbale astroids
@Dao
interface AteroidDoa{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( list: ArrayList <Asteroid>)
    @Query("select * from Asteroid_table where date(closeApproachDate)>=Date('now', 'localtime') and date(closeApproachDate) <=date(:newdate) Order by date(closeApproachDate)")
    fun getasteroids(newdate:String ):LiveData<List<Asteroid>>
    @Query("Delete from Asteroid_table  where date(closeApproachDate)<Date('now', 'localtime')")
    fun deleteunwantedasteroids()
}
//this database has only  1 entity it Asteroid class
@Database(entities = [Asteroid::class], version = 3, exportSchema = false)
abstract class AsteroidDatabase:RoomDatabase(){
    abstract val asteroiddoa: AteroidDoa
    companion object {

        @Volatile
        private var INSTANCE: AsteroidDatabase? = null
        fun getInstance(context: Context): AsteroidDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        "Nasa_Database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}