package com.nureddinelmas.myplacetesting.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nureddinelmas.myplacetesting.model.Places

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(places : Places)

    @Delete
    suspend fun deletePlace(places: Places)

    @Query("SELECT * FROM places")
    fun observePlaces(): LiveData<List<Places>>

/*
    @Query("SELECT * FROM places")
    fun getAllPlaces(): List<Places>

    @Query("DELETE FROM places")
    fun deleteAllDataFromRoom()

 */
}