package com.nasa.gallery.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nasa.gallery.model.NasaItem
import java.lang.reflect.Type
import java.nio.charset.Charset

class FileUtil(private val context: Context) {
    fun getNasaItemListData(): List<NasaItem> {
        val inputStream = context.assets.open("data.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charset.forName("UTF-8"))
        val gson = Gson()
        val listType: Type = object : TypeToken<List<NasaItem>>() {}.type
        return gson.fromJson(json, listType)
    }
}
