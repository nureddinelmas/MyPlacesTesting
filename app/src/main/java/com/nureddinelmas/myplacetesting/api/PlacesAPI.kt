package com.nureddinelmas.myplacetesting.api

import com.nureddinelmas.myplacetesting.util.Constants.API_KEY
import com.nureddinelmas.myplacetesting.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchImg : String,
        @Query("key") apiKey : String = API_KEY
    ) : Response<ImageResponse>
}