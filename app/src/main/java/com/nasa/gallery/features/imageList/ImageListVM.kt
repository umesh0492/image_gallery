package com.nasa.gallery.features.imageList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasa.gallery.data.localsource.LocalDataSource
import com.nasa.gallery.data.model.NasaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageListVM(private val localDataSource: LocalDataSource) : ViewModel() {

    init {
        getPictureList()
    }

    val pictureList = MutableLiveData<List<NasaItem>>()

    private fun getPictureList() {
        viewModelScope.launch(Dispatchers.IO) {
            pictureList.postValue(localDataSource.getNasaItemListData())
        }
    }
}
