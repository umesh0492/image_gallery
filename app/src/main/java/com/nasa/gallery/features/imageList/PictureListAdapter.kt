package com.nasa.gallery.features.imageList

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.nasa.gallery.R
import com.nasa.gallery.data.model.NasaItem
import com.nasa.gallery.utils.loadImage

class PictureListAdapter(
    private val imageLoader: ImageLoader,
    private val click: (position: Int) -> Unit
) : RecyclerView.Adapter<PictureListAdapter.PictureListViewHolder>() {

    private var imageList: List<NasaItem> = emptyList()

    fun setData(data: List<NasaItem>) {
        imageList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return PictureListViewHolder(
            view,
            imageLoader,
            click
        )
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): NasaItem {
        return imageList[position]
    }

    class PictureListViewHolder(
        private val view: View,
        private val imageLoader: ImageLoader,
        private val click: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: NasaItem) {
            val image = view.findViewById<ImageView>(R.id.image_thumbnail)
            val title = view.findViewById<TextView>(R.id.text_photo_title)

            title.text = item.title
            image.loadImage(imageLoader, item.url)
            view.setOnClickListener {
                click(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
