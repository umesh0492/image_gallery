package com.nasa.gallery.localsource

import com.nasa.gallery.model.NasaItem

interface LocalDataSource {
    fun getNasaItemListData(): List<NasaItem>
}
