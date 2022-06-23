package com.example.flickrkotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.flickrkotlin.databinding.FragmentMainBinding
import com.longhb.base.FragmentBase

class MainFragment : FragmentBase<FragmentMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var runnable = Runnable { binding.textView.setBackgroundColor(Color.BLACK) }

        binding.textView.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    binding.textView.postDelayed(runnable, 1500)
                }
                else -> {
                    binding.textView.setBackgroundColor(Color.RED)
                    binding.textView.removeCallbacks(runnable)
                }
            }

            true
        }
    }
}