package com.example.flickrkotlin


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.flickrkotlin.databinding.FragmentMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.longhb.base.FragmentBase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

@DelicateCoroutinesApi
class MainFragment : FragmentBase<FragmentMainBinding>(), NavigationBarView.OnItemSelectedListener {

    private lateinit var adapterViewPager: ViewPagerMainAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterViewPager = ViewPagerMainAdapter(this)

        binding.viewpager.apply {
            adapter = adapterViewPager
            isUserInputEnabled = false
        }

        binding.bnvMain.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.viewpager.currentItem = when (item.itemId) {
            R.id.item_search -> 1
            else -> {
                0
            }
        }
        return true
    }

}

