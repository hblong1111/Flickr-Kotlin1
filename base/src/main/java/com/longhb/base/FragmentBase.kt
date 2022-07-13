package com.longhb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


abstract class FragmentBase<B : ViewDataBinding> : Fragment() {
    open lateinit var binding: B
    open lateinit var navigationViewModel: NavigationViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        navigationViewModel = ViewModelProvider(requireActivity())[NavigationViewModel::class.java]

        onCustomCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

   open fun onCustomCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {

    }

    protected abstract fun getLayoutId(): Int


    protected fun navigation(id: Int) {
        navigationViewModel.idNavigation.postValue(id)
    }
}