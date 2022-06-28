package com.longhb.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


abstract class FragmentNavigationBase<B : ViewDataBinding> : FragmentBase<B>() {

    private var navController: NavController? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNavigation()
        if (navController != null) {
            navigationViewModel.idNavigation.observe(
                requireActivity()
            ) { id: Int? ->
                try {
                    navController!!.navigate(id!!)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun createNavigation() {
        try {
            val fragmentContainerView: FragmentContainerView? = checkViewContainHostFragment()
            if (binding.root is FragmentContainerView) {
                createController(binding.root)
            } else if (fragmentContainerView != null) {
                createController(fragmentContainerView)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkViewContainHostFragment(): FragmentContainerView? {
        val viewGroup = binding.root as ViewGroup
        for (i in 0 until viewGroup.childCount) {
            val view = viewGroup.getChildAt(i)
            if (view is FragmentContainerView) {
                return view
            }
        }
        return null
    }

    private fun createController(view: View) {
        val navHostFragment = childFragmentManager.findFragmentById(view.id) as NavHostFragment?
        if (navHostFragment != null) {
            navController = navHostFragment.navController
        }
    }

    abstract override fun getLayoutId(): Int
}