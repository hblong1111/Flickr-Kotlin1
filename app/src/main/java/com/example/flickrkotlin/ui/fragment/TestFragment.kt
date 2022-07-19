package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.flickrkotlin.R

class TestFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_test, container, false)
    }

//    override fun onStart() {
//        super.onStart()
//
//        val dialog: Dialog? = dialog
//        if (dialog != null) {
//            val width = ViewGroup.LayoutParams.MATCH_PARENT
//            val height = ViewGroup.LayoutParams.MATCH_PARENT
//            dialog.window?.setLayout(width, height)
//        }
//    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("hblong", "TestFragment.onDestroy: ")
    }


    override fun onDetach() {
        super.onDetach()
        Log.d("hblong", "TestFragment.onDetach: ")
    }


    override fun getTheme(): Int {
        return R.style.FullScreenDialog;
    }
}