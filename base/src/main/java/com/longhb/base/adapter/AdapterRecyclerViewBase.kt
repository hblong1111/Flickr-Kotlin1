package com.longhb.base.adapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class AdapterRecyclerViewBase<T : Any, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    private val actionScrollToPosition: Runnable = kotlinx.coroutines.Runnable {
        firstScroll = false
        recyclerView.scrollToPosition(positionLastVisible)
    }
    open lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    open var data: ArrayList<T> = ArrayList()
    open var dataShow: ArrayList<T> = ArrayList()


    var positionLastVisible = 0

    var firstScroll: Boolean = true

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView

        firstScroll = true

        layoutManager = createLayoutManager(recyclerView.context)

        recyclerView.layoutManager = layoutManager

        addScrollGetLastVisiblePosition(recyclerView)
        super.onAttachedToRecyclerView(recyclerView)

    }


    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        if (firstScroll) {
            recyclerView.removeCallbacks(actionScrollToPosition)
            recyclerView.postDelayed(actionScrollToPosition, 1500)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        layoutManager.detachAndScrapAttachedViews(recyclerView.Recycler())
        super.onDetachedFromRecyclerView(recyclerView)
    }

    private fun addScrollGetLastVisiblePosition(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == 0) {
                    positionLastVisible = when (layoutManager) {
                        is StaggeredGridLayoutManager -> (layoutManager as StaggeredGridLayoutManager).findLastCompletelyVisibleItemPositions(
                            null
                        ).maxOrNull()!!
                        is LinearLayoutManager -> (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                        else -> {
                            0
                        }
                    }

                }
            }
        }
        )
        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (dataShow.size < data.size) {
                    setDataShowNew(ArrayList(data.take(itemCount + 1)))
                } else {
                    loadComplete()
                }
            }
        })
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                recyclerView.removeCallbacks(actionScrollToPosition)
//                recyclerView.postDelayed(actionScrollToPosition, 500)
//
//                recyclerView.postDelayed(kotlinx.coroutines.Runnable {
//                    recyclerView.removeOnScrollListener(
//                        this
//                    )
//                }, 1000)
//            }
//
//        }
//        )
    }


    private fun setDataShowNew(newData: ArrayList<T>) {
        val diffUtil: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(DiffCallbackBase(dataShow, newData))

        recyclerView.post {
            diffUtil.dispatchUpdatesTo(this)
        }

        dataShow.clear()
        dataShow.addAll(newData)

    }

    abstract fun createLayoutManager(context: Context): RecyclerView.LayoutManager


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