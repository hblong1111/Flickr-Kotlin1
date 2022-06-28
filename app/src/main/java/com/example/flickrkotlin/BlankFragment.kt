package com.example.flickrkotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flickrkotlin.databinding.FragmentBlankBinding
import com.longhb.base.FragmentBase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class BlankFragment : FragmentBase<FragmentBlankBinding>(), CoroutineScope {
    override fun getLayoutId(): Int {
        return R.layout.fragment_blank
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        job = Job()

        launch {
            delay(3000)
            Log.d("longhb", "BlankFragment.onViewCreated: okk123")
        }

    }

    private val handler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("longhb", "BlankFragment.${throwable.message}")
        }

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + handler + job

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }

}