package com.nasa.gallery

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nasa.gallery.data.localsource.LocalDataSource
import com.nasa.gallery.data.localsource.LocalDataSourceRepository
import com.nasa.gallery.features.imageList.ImageListVM
import com.nasa.gallery.utils.DependencyProvider
import com.nasa.gallery.utils.FileUtil
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageListVmTest {

    lateinit var context: Context
    lateinit var localDataSource: LocalDataSource
    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        DependencyProvider.setApplicationContext(ApplicationProvider.getApplicationContext())
        localDataSource = DependencyProvider.getLocalDataSourceRepository()
    }

    @Test
    fun testPictureList() {
        val viewModel = ImageListVM(localDataSource)
        val value = viewModel.pictureList.getOrAwaitValue()
        Assert.assertThat(value, Matchers.not(CoreMatchers.nullValue()))
    }
}
