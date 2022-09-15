package com.nasa.gallery.data.localsource

import com.nasa.gallery.data.model.NasaItem
import com.nasa.gallery.utils.FileUtil

class LocalDataSourceRepository(private val fileUtil: FileUtil) : LocalDataSource {
    override fun getNasaItemListData(): List<NasaItem> {
        return fileUtil.getNasaItemListData()
    }
}
