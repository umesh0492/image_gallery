package com.nasa.gallery.features.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import coil.ImageLoader
import com.nasa.gallery.features.INTENT_IMAGE_LIST_DATA
import com.nasa.gallery.features.INTENT_SELECTED_ITEM_POSITION
import com.nasa.gallery.R
import com.nasa.gallery.utils.DependencyProvider
import com.nasa.gallery.utils.ViewPagerAnimation

class ImageDetailsFragment : Fragment() {

    private val viewModel: ImageDetailVM by lazy {
        ViewModelProvider(
            viewModelStore,
            DependencyProvider.vmFactory
        )[ImageDetailVM::class.java]
    }

    private val imageLoader: ImageLoader by lazy { DependencyProvider.getImageLoader() }
    private val detailsPagerAdapter: DetailsPagerAdapter by lazy { DetailsPagerAdapter(imageLoader) }

    private lateinit var viewPagerPhotos: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_picture_details, container, false)
        setupViews(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resolveIntentData()
        setupViewPager()
    }

    private fun resolveIntentData() {
        viewModel.currentItemPosition = arguments?.getInt(INTENT_SELECTED_ITEM_POSITION) ?: 0
        viewModel.pictureList =
            arguments?.getParcelableArrayList(INTENT_IMAGE_LIST_DATA) ?: emptyList()
    }

    private fun setupViews(view: View) {
        viewPagerPhotos = view.findViewById(R.id.view_pager_photos)
    }

    private fun setupViewPager() {
        detailsPagerAdapter.setData(viewModel.pictureList)
        viewPagerPhotos.apply {
            adapter = detailsPagerAdapter
            setCurrentItem(viewModel.currentItemPosition, false)
            setPageTransformer(ViewPagerAnimation())
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.currentItemPosition = viewPagerPhotos.currentItem
    }

    override fun onResume() {
        super.onResume()
        viewPagerPhotos.setCurrentItem(viewModel.currentItemPosition, false)
    }
}
