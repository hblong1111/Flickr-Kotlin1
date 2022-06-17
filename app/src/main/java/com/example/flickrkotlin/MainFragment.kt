package com.example.flickrkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flickrkotlin.databinding.FragmentMainBinding
import com.longhb.base.FragmentBase

class MainFragment : FragmentBase<FragmentMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }
}