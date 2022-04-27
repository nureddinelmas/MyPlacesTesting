package com.nureddinelmas.myplacetesting.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.nureddinelmas.myplacetesting.R
import com.nureddinelmas.myplacetesting.databinding.FragmentPlaceDetailsBinding
import com.nureddinelmas.myplacetesting.util.Status
import com.nureddinelmas.myplacetesting.viewmodel.PlaceViewModel
import javax.inject.Inject

class PlaceDetailsFragment @Inject constructor(private val glide : RequestManager): Fragment(R.layout.fragment_place_details) {
    private var fragmentBinding : FragmentPlaceDetailsBinding? = null
    lateinit var viewModel: PlaceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPlaceDetailsBinding.bind(view)
        fragmentBinding = binding
        viewModel = ViewModelProvider(requireActivity()).get(PlaceViewModel::class.java)
        subscribeToObservers()
        binding.placeImageView.setOnClickListener {
            findNavController().navigate(PlaceDetailsFragmentDirections.actionPlaceDetailsFragmentToImageApiFragment())
        }

        // kullanici geri tusuna basinca ne olcak
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                // pop back stack, nereden geldi ise oraya geri dön.
               findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.saveButton.setOnClickListener {
            viewModel.makePlace(
                binding.nameText.text.toString(),
                binding.cityNameText.text.toString(),
                binding.visitYearText.text.toString())
        }
    }


    private fun subscribeToObservers(){
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url->
            fragmentBinding?.let { binding ->
                glide.load(url).into(binding.placeImageView)
            }
        })

        viewModel.insertPlaceMessage.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }
            }
        })
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()

    }
}
