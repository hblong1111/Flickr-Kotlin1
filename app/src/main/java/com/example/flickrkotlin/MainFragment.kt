package com.example.flickrkotlin


import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.flickrkotlin.databinding.FragmentMainBinding
import com.longhb.base.FragmentBase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

@DelicateCoroutinesApi
class MainFragment : FragmentBase<FragmentMainBinding>(), CoroutineScope {

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swrl.setOnRefreshListener {

            launch {
                delay(1000)

                binding.swrl.isRefreshing = false

                navigation(R.id.action_mainFragment_to_blankFragment)
            }

        }


    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("longhb", "MainFragment.$exception: ")
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + handler
}

