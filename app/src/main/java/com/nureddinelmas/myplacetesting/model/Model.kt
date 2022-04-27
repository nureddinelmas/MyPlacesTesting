package com.nureddinelmas.myplacetesting.model

import androidx.room.*

@Entity("places")
data class Places(

    @ColumnInfo("name")
    var name: String,

    @ColumnInfo("cityName")
    var cityName: String,

    @ColumnInfo("visitYear")
    var visitYear: Int,

    @ColumnInfo("imageUrl")
    var imageUrl : String)

{
     @PrimaryKey(true)
     var id : Int? = null

}