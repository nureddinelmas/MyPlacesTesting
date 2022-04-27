package com.nureddinelmas.myplacetesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nureddinelmas.myplacetesting.R
import com.nureddinelmas.myplacetesting.adapter.PlaceRecyclerAdapter
import com.nureddinelmas.myplacetesting.databinding.FragmentMyplaceBinding
import com.nureddinelmas.myplacetesting.viewmodel.PlaceViewModel
import javax.inject.Inject

class MyPlaceFragment @Inject constructor(val placeRecyclerAdapter: PlaceRecyclerAdapter) : Fragment(R.layout.fragment_myplace) {
    private var fragmentBinding: FragmentMyplaceBinding? = null
    lateinit var viewModel: PlaceViewModel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            val layoutPosition = viewHolder.layoutPosition
            val selectedPlace = placeRecyclerAdapter.place[layoutPosition]
            viewModel.deletePlace(selectedPlace)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PlaceViewModel::class.java)

        val binding = FragmentMyplaceBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()
        binding.recyclerViewPlaces.adapter = placeRecyclerAdapter
        binding.recyclerViewPlaces.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewPlaces)

        binding.fab.setOnClickListener {
            findNavController().navigate(MyPlaceFragmentDirections.actionMyPlaceFragmentToPlaceDetailsFragment())
        }
    }

    private fun subscribeToObservers(){
        viewModel.placeList.observe(viewLifecycleOwner, Observer {
            placeRecyclerAdapter.place = it
        })

    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}