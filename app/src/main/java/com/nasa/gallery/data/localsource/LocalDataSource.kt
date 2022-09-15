package com.nasa.gallery.data.localsource

import com.nasa.gallery.data.model.NasaItem

interface LocalDataSource {
    fun getNasaItemListData(): List<NasaItem>
}
