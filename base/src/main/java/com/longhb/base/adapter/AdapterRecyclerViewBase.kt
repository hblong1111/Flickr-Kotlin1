package com.longhb.base.adapter

import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

abstract class AdapterRecyclerViewBase<T : Any> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    open lateinit var recyclerView: RecyclerView
    abstract var layoutManager: RecyclerView.LayoutManager

    abstract var data: ArrayList<T>
    abstract var dataShow: ArrayList<T>

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (dataShow.size < data.size) {
                    this@AdapterRecyclerViewBase.onLoadMore(page, totalItemsCount, view)
                } else {
                    loadComplete()
                }
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }


    abstract fun loadComplete()
    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

}