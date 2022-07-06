package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api.FlickrResult
import com.example.flickrkotlin.R
import com.example.flickrkotlin.adapter.ExploreAdapter
import com.example.flickrkotlin.databinding.FragmentExploreBinding
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.FragmentBase

class ExploreFragment : FragmentBase<FragmentExploreBinding>(),
    ExploreAdapter.ExploreAdapterCallback {
    private lateinit var photoViewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModel = ViewModelProvider(requireActivity())[PhotoViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_explore
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = ExploreAdapter(this)

        binding.rcv.adapter = adapter

        photoViewModel.photosLiveData.observe(requireActivity(), Observer {
            adapter.setDataNew(ArrayList(it))
        })

        photoViewModel.isLoadData.observe(requireActivity(), Observer {
            if (binding.rcv.visibility == View.INVISIBLE) {

                binding.groupLoading.visibility = if (it) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
                binding.rcv.visibility = if (!it) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            } else {
                Toast.makeText(requireActivity(), "Loading ...", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun loadComplete() {
        photoViewModel.getListPhoto()
    }


}