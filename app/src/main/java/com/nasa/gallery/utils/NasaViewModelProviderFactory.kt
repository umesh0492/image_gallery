package com.nasa.gallery.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasa.gallery.data.localsource.LocalDataSource
import com.nasa.gallery.features.MainActivityVM
import com.nasa.gallery.features.imageList.ImageListVM
import com.nasa.gallery.features.imagedetail.ImageDetailVM

class NasaViewModelProviderFactory(
    private val localDataSource: LocalDataSource
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ImageListVM::class.java)) {
            return ImageListVM(localDataSource) as T
        } else if (modelClass.isAssignableFrom(ImageDetailVM::class.java)) {
            return ImageDetailVM() as T
        } else if (modelClass.isAssignableFrom(MainActivityVM::class.java)) {
            return MainActivityVM() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
