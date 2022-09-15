package com.nasa.gallery.features.imageList

import com.nasa.gallery.data.model.NasaItem

interface ImageItemClick {
    fun onClick(position: Int, nasaItemList: List<NasaItem>)
}