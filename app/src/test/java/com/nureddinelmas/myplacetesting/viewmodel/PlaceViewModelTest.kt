package com.nureddinelmas.myplacetesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nureddinelmas.myplacetesting.model.Places
import com.nureddinelmas.myplacetesting.repo.FakePlaceRepository
import com.nureddinelmas.myplacetesting.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class PlaceViewModelTest {

    @get:Rule
    var instantTaskExecutorRule : TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel:PlaceViewModel

    @Before
    fun setup(){
        // Test Doubles

      viewModel = PlaceViewModel(FakePlaceRepository())
    }


    @Test
    fun `ìnsert Place without visit year returns error` (){
        viewModel.makePlace("turkey", "istanbul", "")
        val value = viewModel.insertPlaceMessage.getOrAwaitValue()

        assertThat(value.status).isEqualTo(Status.SUCCESS)

    }

    @Test
    fun `ìnsert Place without land name returns error` (){
        viewModel.makePlace("", "istanbul", "2017")
        // assertThat(viewModel.insertPlaceMessage.getOrAwaitValue().status).isEqualTo(Status.ERROR)

        assertThat(viewModel.insertPlace(Places("nureddin","elmas", 2212,"bla bla"))).isEqualTo(Status.ERROR)
    }

    @Test
    fun `ìnsert Place without city name returns error` (){
        viewModel.makePlace("turkey","","2012")
        assertThat(viewModel.insertPlaceMessage.getOrAwaitValue().status).isEqualTo(Status.ERROR)
    }

}