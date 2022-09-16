package com.nasa.gallery

import android.app.Application
import com.nasa.gallery.utils.DependencyProvider

class NasaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyProvider.setApplicationContext(this)
    }
}
