package com.longhb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    private val idNavigation: MutableLiveData<Int> = MutableLiveData()

    fun getIdNavigation(): MutableLiveData<Int> {
        return idNavigation
    }

    fun setIdNavigation(value: Int) {
        idNavigation.postValue(value)
    }
}