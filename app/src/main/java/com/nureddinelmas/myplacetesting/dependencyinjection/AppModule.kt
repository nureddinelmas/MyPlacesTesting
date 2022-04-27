package com.nureddinelmas.myplacetesting.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nureddinelmas.myplacetesting.R
import com.nureddinelmas.myplacetesting.util.Constants.BASE_URL
import com.nureddinelmas.myplacetesting.api.PlacesAPI
import com.nureddinelmas.myplacetesting.repo.PlaceRepository
import com.nureddinelmas.myplacetesting.repo.PlaceRepositoryInterface
import com.nureddinelmas.myplacetesting.roomdatabase.PlaceDao
import com.nureddinelmas.myplacetesting.roomdatabase.PlacesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, PlacesDatabase::class.java, "placedatabase").build()


    @Singleton
    @Provides
    fun injectDao(database: PlacesDatabase) = database.placeDao()


    @Singleton
    @Provides
    fun injectionRetrofitAPI() : PlacesAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PlacesAPI::class.java)
    }


    @Singleton
    @Provides
    fun injectNormalRepo(dao: PlaceDao, api:PlacesAPI) = PlaceRepository(dao, api) as PlaceRepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )
}