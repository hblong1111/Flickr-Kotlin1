package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.api.FlickrResult
import com.example.flickrkotlin.LoadMoreCallback
import com.example.flickrkotlin.R
import com.example.flickrkotlin.adapter.ExploreAdapter
import com.example.flickrkotlin.databinding.FragmentExploreBinding
import com.example.flickrkotlin.layout_manager.MyLayoutManage
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.FragmentBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExploreFragment : FragmentBase<FragmentExploreBinding>(), LoadMoreCallback {
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

        adapter = ExploreAdapter(this)

        binding.rcv.adapter = adapter
//        binding.rcv.layoutManager =
//            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rcv.layoutManager = LinearLayoutManager(context)


        photoViewModel.photos.observe(requireActivity(), Observer {
            data.clear()
            data.addAll(it)
            loadMore(0)
        })
    }

    override fun loadMore(position: Int) {
//        binding.rcv.post {
//            val dataLoadMore = ArrayList<FlickrResult.Photos.Photo>()
//            for (i in 0..30) {
//                if (position + i < data.size) {
//                    dataLoadMore.add(data[position + i])
//                }
//            }
//            adapter.setData(dataLoadMore)
//        }
        Log.d("longhb", "ExploreFragment.loadMore: $position")
        binding.rcv.post {
            if (position + 1 < data.size) {
                adapter.addItem(data[position + 1])

            }
        }
    }

}