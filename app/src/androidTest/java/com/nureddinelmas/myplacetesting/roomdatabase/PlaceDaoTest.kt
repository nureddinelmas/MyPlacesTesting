package com.nureddinelmas.myplacetesting.roomdatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.nureddinelmas.myplacetesting.model.Places
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class PlaceDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)




    @Inject
    @Named("testDatabase")
    lateinit var database: PlacesDatabase

    private lateinit var dao : PlaceDao

    @Before
    fun setup(){
        /*
        // in memory gecici hafiza da kayit edip islem bitince siliniyor. Testler icin gerekli olan durum.
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), PlacesDatabase::class.java).allowMainThreadQueries().build()
         */
        hiltRule.inject()
        dao = database.placeDao()

    }


    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertPlaceTesting() = runBlocking{
            val examplePlace = Places("Nureddin", "Elmas", 2022, "test.com")
            dao.insertPlace(examplePlace)

       val list =  dao.observePlaces().getOrAwaitValue()

        assertThat(list).contains(examplePlace)


    }

    @Test
    fun deletePlaceTesting() = runBlocking{

        val examData = Places("Nu", "Elm", 323, "tes.com")
        dao.insertPlace(examData)

        dao.deletePlace(examData)

        val list = dao.observePlaces().getOrAwaitValue()

        assertThat(list).doesNotContain(examData)
    }

}