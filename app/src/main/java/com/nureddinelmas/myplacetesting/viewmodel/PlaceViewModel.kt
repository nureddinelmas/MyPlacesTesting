package com.nureddinelmas.myplacetesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nureddinelmas.myplacetesting.model.ImageResponse
import com.nureddinelmas.myplacetesting.model.Places
import com.nureddinelmas.myplacetesting.repo.PlaceRepositoryInterface
import com.nureddinelmas.myplacetesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(private val repository: PlaceRepositoryInterface): ViewModel() {


    // Place Fragment
    val placeList = repository.getPlace()

    // Image API Fragment
    private var images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>> get() = images

    private var selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String> get() = selectedImage

    // Place Details Fragment
    private var insertPlaceMsg = MutableLiveData<Resource<Places>>()
    val insertPlaceMessage : LiveData<Resource<Places>> get() = insertPlaceMsg

    fun resetInsertMsg() {
        insertPlaceMsg = MutableLiveData<Resource<Places>>()
    }




    fun setSelectedImage(url : String){
        selectedImage.postValue(url)
    }


    fun deletePlace(places: Places) = viewModelScope.launch{
        repository.deletePlace(places)
    }


    fun insertPlace(places: Places) = viewModelScope.launch {
        repository.insertPlace(places)
    }

    fun makePlace(name: String, cityName: String, visitYear: String){
        if(name.isEmpty() || cityName.isEmpty() || visitYear.isEmpty()){
            insertPlaceMsg.postValue(Resource.error("Enter name, city name and visit year", null))
            return
        }

        val visitYearInt = try {
            visitYear.toInt()
        }catch (e: Exception){
            insertPlaceMsg.postValue(Resource.error("Visit Year should be number", null))
            return
        }

        val place = Places(name, cityName, visitYearInt, selectedImage.value?: "")

        insertPlace(place)
        setSelectedImage("")
        insertPlaceMsg.postValue(Resource.success(place))
    }

    fun searchForImage(searchString: String){
        if(searchString.isEmpty()){
            return
        }

        images.value = Resource.loading(null)

        viewModelScope.launch {
            val response =  repository.searchImage(searchString)
            images.value = response
        }
    }
}