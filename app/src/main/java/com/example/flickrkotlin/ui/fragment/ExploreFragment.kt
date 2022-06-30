package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.api.FlickrResult
import com.example.flickrkotlin.EndlessRecyclerViewScrollListener
import com.example.flickrkotlin.R
import com.example.flickrkotlin.adapter.ExploreAdapter
import com.example.flickrkotlin.databinding.FragmentExploreBinding
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.FragmentBase

class ExploreFragment : FragmentBase<FragmentExploreBinding>() {
    private lateinit var adapter: ExploreAdapter
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var data: ArrayList<FlickrResult.Photos.Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModel = ViewModelProvider(requireActivity())[PhotoViewModel::class.java]
        data = ArrayList()

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_explore
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExploreAdapter()

        binding.rcv.adapter = adapter
        val layoutManager =  StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        binding.rcv.layoutManager = layoutManager
//        binding.rcv.layoutManager = LinearLayoutManager(context)
        binding.rcv.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Log.d("longhb", "ExploreFragment.onLoadMore: $page | $totalItemsCount")


                adapter.setData(ArrayList(data.take(adapter.itemCount+30)))
                (mLayoutManager as StaggeredGridLayoutManager?)!!.invalidateSpanAssignments()

            }
        })

        binding.rcv.addOnScrollListener(object  : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })


        photoViewModel.photos.observe(requireActivity(), Observer {
            data.clear()
            data.addAll(it)
            adapter.setData(ArrayList(data.take(adapter.itemCount+30)))
        })
    }


}