package com.nasa.gallery.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityVM : ViewModel() {

    var currentScreen = MutableLiveData<Screen>(Screen.ListFragment)

    fun updateCurrentScreen(screen : Screen){
        currentScreen.value = screen
    }
}
