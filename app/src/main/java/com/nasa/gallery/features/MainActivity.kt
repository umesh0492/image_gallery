package com.nasa.gallery.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.nasa.gallery.features.imagedetail.ImageDetailsFragment
import com.nasa.gallery.R
import com.nasa.gallery.data.model.NasaItem
import com.nasa.gallery.features.imageList.ImageItemClick
import com.nasa.gallery.features.imageList.ImageListFragment
import com.nasa.gallery.utils.DependencyProvider

const val INTENT_SELECTED_ITEM_POSITION = "position"
const val INTENT_IMAGE_LIST_DATA = "data"

class MainActivity : AppCompatActivity(), ImageItemClick {

    private val viewModel: MainActivityVM by lazy {
        ViewModelProvider(
            viewModelStore,
            DependencyProvider.vmFactory
        )[MainActivityVM::class.java]
    }

    private val imageListFragment: ImageListFragment by lazy { ImageListFragment() }
    private val imageDetailsFragment: ImageDetailsFragment by lazy { ImageDetailsFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addScreenObserver()
    }

    private fun addScreenObserver() {
        viewModel.currentScreen.observe(this) { screen ->
            when (screen) {
                is Screen.DetailFragment -> {
                    val bundle = getImageDetailBundle(screen.position, screen.listData)
                    openImageDetailFragment(bundle)
                }
                is Screen.ListFragment -> openImageListFragment()
            }
        }
    }

    override fun onClick(position: Int, nasaItemList: List<NasaItem>) {
        viewModel.updateCurrentScreen(Screen.DetailFragment(position, nasaItemList))
    }

    private fun openImageListFragment() {
        openFragment(imageListFragment)
        imageListFragment.setImageItemClick(this)
    }

    override fun onBackPressed() {
        if (viewModel.currentScreen.value == Screen.ListFragment) {
            super.onBackPressed()
        } else {
            updateCurrentScreen(Screen.ListFragment)
        }
    }

    private fun updateCurrentScreen(screen: Screen) {
        viewModel.updateCurrentScreen(screen)
    }

    private fun openImageDetailFragment(args: Bundle) {
        imageDetailsFragment.arguments = args
        openFragment(imageDetailsFragment)
    }

    private fun getImageDetailBundle(position: Int, nasaItemList: List<NasaItem>): Bundle {
        val args = Bundle()
        args.putParcelableArrayList(INTENT_IMAGE_LIST_DATA, nasaItemList as ArrayList)
        args.putInt(INTENT_SELECTED_ITEM_POSITION, position)
        return args
    }

    private fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(
            R.id.fragment_container,
            fragment
        )
        transaction.commit()
    }
}
