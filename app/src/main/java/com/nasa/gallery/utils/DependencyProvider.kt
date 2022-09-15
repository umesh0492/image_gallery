package com.nasa.gallery.utils

import android.app.Application
import coil.ImageLoader
import com.nasa.gallery.data.localsource.LocalDataSource
import com.nasa.gallery.data.localsource.LocalDataSourceRepository

object DependencyProvider {

    private lateinit var context: Application


    fun getApplicationContext(): Application {
        return context
    }

    fun setApplicationContext(application: Application) {
        context = application
    }

    fun getImageLoader(): ImageLoader {
        return ImageLoader.Builder(context).build()
    }

    private fun getFileUtil(): FileUtil {
        return FileUtil(context)
    }

    private fun getLocalDataSourceRepository(): LocalDataSource {
        return LocalDataSourceRepository(getFileUtil())
    }
}
