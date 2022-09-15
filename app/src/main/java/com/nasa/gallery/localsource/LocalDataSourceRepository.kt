package com.nasa.gallery.localsource

import com.nasa.gallery.model.NasaItem
import com.nasa.gallery.utils.FileUtil

class LocalDataSourceRepository(private val fileUtil: FileUtil) : LocalDataSource {
    override fun getNasaItemListData(): List<NasaItem> {
        return fileUtil.getNasaItemListData()
    }
}
