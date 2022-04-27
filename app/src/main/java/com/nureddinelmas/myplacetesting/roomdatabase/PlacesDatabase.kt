package com.nureddinelmas.myplacetesting.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nureddinelmas.myplacetesting.model.Places

@Database(entities = [Places::class], version = 1)
abstract class PlacesDatabase : RoomDatabase(){

    abstract fun placeDao() : PlaceDao

    /*
    companion object{
       @Volatile private var instance : PlacesDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also{
                instance = it
            }
        }


        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PlacesDatabase::class.java,
            "placesdatabase").build()
    }
     */

}