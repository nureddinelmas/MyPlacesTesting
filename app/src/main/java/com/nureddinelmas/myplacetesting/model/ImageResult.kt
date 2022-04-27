package com.nureddinelmas.myplacetesting.model

data class ImageResult(
    val id: Int,
    val pageUrl : String,
    val type : String,
    val tags : String,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int,
    val webformatHeigth: Int,
    val largeImageUrl: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views : String,
    val downloads: Int,
    val collections: Int,
    val likes: Int,
    val comments: Int,
    val user_id: Int,
    val user : String,
    val userImageUrl: String
)
