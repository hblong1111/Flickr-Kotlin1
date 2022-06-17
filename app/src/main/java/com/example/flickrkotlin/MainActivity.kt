package com.example.flickrkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.example.flickrkotlin.databinding.ActivityMainBinding
import com.longhb.base.ActivityNavigationBase

class MainActivity : ActivityNavigationBase<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}