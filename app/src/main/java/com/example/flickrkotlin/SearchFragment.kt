package com.example.flickrkotlin

import com.example.flickrkotlin.databinding.FragmentExploreBinding
import com.example.flickrkotlin.databinding.FragmentSearchBinding
import com.longhb.base.FragmentBase

class SearchFragment : FragmentBase<FragmentSearchBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }
}