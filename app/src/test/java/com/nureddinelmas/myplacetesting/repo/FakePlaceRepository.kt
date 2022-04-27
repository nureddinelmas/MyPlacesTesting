package com.nureddinelmas.myplacetesting.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nureddinelmas.myplacetesting.model.ImageResponse
import com.nureddinelmas.myplacetesting.model.Places
import com.nureddinelmas.myplacetesting.util.Resource

class FakePlaceRepository : PlaceRepositoryInterface {

    private val place = mutableListOf<Places>()
    private val placeLiveData = MutableLiveData<List<Places>>(place)
    override suspend fun insertPlace(places: Places) {
       place.add(places)
        refreshData()
    }

    override suspend fun deletePlace(places: Places) {
        place.remove(places)
        refreshData()
    }

    override fun getPlace(): LiveData<List<Places>> {
       return placeLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(), 0,0))
    }


    private fun refreshData(){
        placeLiveData.postValue(place)
    }
}