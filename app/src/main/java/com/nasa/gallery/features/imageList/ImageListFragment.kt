package com.nasa.gallery.features.imageList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.nasa.gallery.R
import com.nasa.gallery.utils.DependencyProvider

class ImageListFragment : Fragment() {

    private val viewModel: ImageListVM by lazy {
        ViewModelProvider(
            requireActivity(),
            DependencyProvider.vmFactory
        )[ImageListVM::class.java]
    }

    private val imageLoader: ImageLoader = DependencyProvider.getImageLoader()

    private lateinit var pictureListRecyclerView: RecyclerView

    private var imageItemClickListener: ImageItemClick? = null

    private val pictureAdapter: PictureListAdapter by lazy {
        PictureListAdapter(imageLoader) { position ->
            imageItemClickListener?.onClick(position, viewModel.pictureList.value ?: emptyList())
        }
    }

    fun setImageItemClick(itemClickListener: ImageItemClick) {
        imageItemClickListener = itemClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_picture_list, container, false)
        setupViews(view)
        setupRecyclerView()
        setupObservers()
        return view
    }

    private fun setupObservers() {
        viewModel.pictureList.observe(viewLifecycleOwner) { list ->
            pictureAdapter.setData(list)
        }
    }

    private fun setupViews(view: View) {
        pictureListRecyclerView = view.findViewById(R.id.rv_picture_list)
    }

    private fun setupRecyclerView() {
        pictureListRecyclerView.apply {
            adapter = pictureAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}
