package com.nureddinelmas.myplacetesting.repo

import androidx.lifecycle.LiveData
import com.nureddinelmas.myplacetesting.util.Resource
import com.nureddinelmas.myplacetesting.api.PlacesAPI
import com.nureddinelmas.myplacetesting.model.ImageResponse
import com.nureddinelmas.myplacetesting.model.Places
import com.nureddinelmas.myplacetesting.roomdatabase.PlaceDao
import java.lang.Exception
import javax.inject.Inject

class PlaceRepository @Inject constructor(private val placeDao: PlaceDao, private val placesAPI: PlacesAPI) : PlaceRepositoryInterface {

    override suspend fun insertPlace(places: Places) {
       placeDao.insertPlace(places)
    }

    override suspend fun deletePlace(places: Places) {
        placeDao.deletePlace(places)
    }

    override fun getPlace(): LiveData<List<Places>> {
       return placeDao.observePlaces()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
       return try {
           val response = placesAPI.imageSearch(imageString)
           if (response.isSuccessful){
               response.body()?.let {
                   return@let Resource.success(it)
               } ?: Resource.error("Error", null)
           } else {
               Resource.error("Error", null)
           }
       } catch (e : Exception){
           Resource.error("No Data", null)
       }
    }
}