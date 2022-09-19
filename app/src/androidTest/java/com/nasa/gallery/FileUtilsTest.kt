package com.nasa.gallery

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nasa.gallery.data.model.NasaItem
import com.nasa.gallery.utils.FileUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FileUtilsTest {

    lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testDataNotNull() {
        val logic = FileUtil(context)
        val data = logic.getNasaItemListData()
        Assert.assertNotNull(data)
    }
}