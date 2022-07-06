package com.longhb.base.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

abstract class AdapterRecyclerViewBase<T : Any, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    open lateinit var recyclerView: RecyclerView
    abstract var layoutManager: RecyclerView.LayoutManager

    open var data: ArrayList<T> = ArrayList()
    open var dataShow: ArrayList<T> = ArrayList()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (dataShow.size < data.size) {
                    setDataShowNew(ArrayList(data.take(itemCount + 1)))
                } else {
                    loadComplete()
                }
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }


    private fun setDataShowNew(newData: ArrayList<T>) {
        var diffUtil: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(DiffCallbackBase(dataShow, newData))
        diffUtil.dispatchUpdatesTo(this)
        dataShow.clear()
        dataShow.addAll(newData)

    }


    abstract fun loadComplete()

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    inner class DiffCallbackBase(var oldData: ArrayList<T>, var newData: ArrayList<T>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldData[oldItemPosition]
            val newItem = newData[newItemPosition]
            return this@AdapterRecyclerViewBase.areItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldData[oldItemPosition]
            val newItem = newData[newItemPosition]
            return this@AdapterRecyclerViewBase.areContentsTheSame(oldItem, newItem)
        }


    }


    fun setDataNew(data: ArrayList<T>) {
        this.data.clear()
        this.data.addAll(data)
        setDataShowNew(ArrayList(data.take(itemCount + 1)))

    }


    override fun getItemCount(): Int {
        return dataShow.size
    }
}