package com.nureddinelmas.myplacetesting.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.nureddinelmas.myplacetesting.roomdatabase.PlacesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("testDatabase")
    fun injectionInMemoryRoom(@ApplicationContext context : Context) =
        Room.inMemoryDatabaseBuilder(context, PlacesDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}