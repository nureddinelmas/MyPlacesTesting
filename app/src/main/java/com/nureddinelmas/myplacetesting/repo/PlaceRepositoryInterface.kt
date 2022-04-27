package com.nureddinelmas.myplacetesting.repo

import androidx.lifecycle.LiveData
import com.nureddinelmas.myplacetesting.util.Resource
import com.nureddinelmas.myplacetesting.model.ImageResponse
import com.nureddinelmas.myplacetesting.model.Places


interface PlaceRepositoryInterface {

    suspend fun insertPlace(places: Places)

    suspend fun deletePlace(places: Places)

    fun getPlace() : LiveData<List<Places>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>
}