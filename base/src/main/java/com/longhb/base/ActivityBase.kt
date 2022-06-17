package com.longhb.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class ActivityBase<B : ViewDataBinding> : AppCompatActivity() {

    open lateinit var binding: B

    open lateinit var navigationViewModel: NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), null, false)

        navigationViewModel = ViewModelProvider(this)[NavigationViewModel::class.java]
    }

    protected abstract fun getLayoutId(): Int

    protected open fun navigation(id: Int) {
        navigationViewModel.setIdNavigation(id)
    }
}