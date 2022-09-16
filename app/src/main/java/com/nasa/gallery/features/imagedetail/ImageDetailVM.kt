package com.nasa.gallery.features.imagedetail

import androidx.lifecycle.ViewModel
import com.nasa.gallery.data.model.NasaItem

class ImageDetailVM : ViewModel() {
    var currentItemPosition = 0
    var pictureList: List<NasaItem> = emptyList()
}