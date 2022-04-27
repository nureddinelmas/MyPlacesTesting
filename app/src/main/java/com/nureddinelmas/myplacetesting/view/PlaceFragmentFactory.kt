package com.nureddinelmas.myplacetesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.nureddinelmas.myplacetesting.adapter.ImageRecyclerAdapter
import com.nureddinelmas.myplacetesting.adapter.PlaceRecyclerAdapter
import javax.inject.Inject

class PlaceFragmentFactory @Inject constructor(
    private val glide : RequestManager,
    private val placeRecyclerAdapter: PlaceRecyclerAdapter,
    private val imageRecyclerAdapter: ImageRecyclerAdapter
    ) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            MyPlaceFragment::class.java.name -> MyPlaceFragment(placeRecyclerAdapter)
            PlaceDetailsFragment::class.java.name -> PlaceDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }

}