package com.longhb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider


abstract class DialogFragmentBase<B : ViewDataBinding> : DialogFragment() {
    open lateinit var binding: B
    open lateinit var navigationViewModel: NavigationViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (setAnimationCustom() != 0) {
            dialog?.window?.attributes?.windowAnimations = setAnimationCustom()
        }
    }

    open fun setAnimationCustom(): Int {
        return 0
    }

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

    override fun getTheme(): Int {
        return R.style.FullScreenDialog;
    }
}