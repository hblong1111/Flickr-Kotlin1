package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flickrkotlin.R
import com.example.flickrkotlin.adapter.ExploreAdapter
import com.example.flickrkotlin.databinding.FragmentExploreBinding
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.FragmentBase

class ExploreFragment : FragmentBase<FragmentExploreBinding>(),
    ExploreAdapter.ExploreAdapterCallback {
    private lateinit var photoViewModel: PhotoViewModel

    private lateinit var adapter: ExploreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModel = ViewModelProvider(requireActivity())[PhotoViewModel::class.java]
        adapter = photoViewModel.getExploreAdapter(this)

        photoViewModel.photosLiveData.observe(requireActivity(), Observer {
            adapter.setDataNew(ArrayList(it))
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_explore
    }


    override fun loadComplete() {
        photoViewModel.getListPhoto()
    }

    override fun onClickItem(view: View, position: Int) {
        photoViewModel.positionSelect = position
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.add(R.id.containerFRMain, ImageDetailFragment())
//            ?.addToBackStack(null)
//            ?.commit()

        navigation(R.id.action_mainFragment_to_imageDetailFragment)


    }

    override fun onCustomCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {


        binding.rcv.adapter = adapter


        photoViewModel.positionFocus.observe(requireActivity(), Observer {
            if (it >= 0) {
                adapter.positionLastVisible = it
                binding.rcv.scrollToPosition(it)
            }
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
            } else if (it) {
                Toast.makeText(requireActivity(), "Loading ...", Toast.LENGTH_SHORT).show()
            }

        })

    }

}