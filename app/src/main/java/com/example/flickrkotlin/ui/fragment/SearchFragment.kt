package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.flickrkotlin.R
import com.example.flickrkotlin.databinding.FragmentSearchBinding
import com.longhb.base.FragmentBase

class SearchFragment : FragmentBase<FragmentSearchBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun onCustomCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        TODO("Not yet implemented")
    }

}