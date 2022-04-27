package com.nureddinelmas.myplacetesting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.nureddinelmas.myplacetesting.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Hilt te uygulamanain nereden baslayacagini belirtmemiz gerekiyor.
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var fragmentFactory: PlaceFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }
}