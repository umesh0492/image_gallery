package com.nasa.gallery.features

import com.nasa.gallery.data.model.NasaItem

sealed class Screen {
    object ListFragment : Screen()
    data class DetailFragment(val position: Int, val listData: List<NasaItem>) : Screen()
}