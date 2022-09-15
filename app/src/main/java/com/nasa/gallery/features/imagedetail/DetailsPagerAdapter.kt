package com.nasa.gallery.features.imagedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.nasa.gallery.R
import com.nasa.gallery.data.model.NasaItem
import com.nasa.gallery.utils.loadImage

class DetailsPagerAdapter(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<DetailsPagerAdapter.DetailsPagerViewHolder>() {

    private var imageList: List<NasaItem> = emptyList()

    fun setData(data: List<NasaItem>) {
        imageList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsPagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_picture_details, parent, false)
        return DetailsPagerViewHolder(view, imageLoader)
    }

    override fun onBindViewHolder(holder: DetailsPagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): NasaItem {
        return imageList[position]
    }

    class DetailsPagerViewHolder(
        private val view: View,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: NasaItem) {
            val imageDetails = view.findViewById<ImageView>(R.id.image_details)
            val textTitle = view.findViewById<TextView>(R.id.text_title)
            val textDate = view.findViewById<TextView>(R.id.text_date)
            val textExplanation = view.findViewById<TextView>(R.id.text_explanation)
            val textCopyright = view.findViewById<TextView>(R.id.text_copyright)

            textTitle.text = item.title
            textDate.text = item.date
            textExplanation.text = item.explanation
            imageDetails.loadImage(imageLoader, item.url)
            textCopyright.text = item.copyright ?: "none"
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
