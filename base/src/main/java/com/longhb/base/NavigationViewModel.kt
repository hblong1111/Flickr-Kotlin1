package com.longhb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val idNavigation: MutableLiveData<Int> = MutableLiveData()
}