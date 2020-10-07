package com.adrian.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.core.R
import com.adrian.core.domain.model.Restoran
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class RestoranAdapter : RecyclerView.Adapter<RestoranAdapter.ViewHolder>() {

    private val listData = ArrayList<Restoran>()
    var onItemClick: ((Restoran) -> Unit)? = null

    fun setData(newListData: List<Restoran>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestoranAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: RestoranAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Restoran) {
            with(itemView) {

                Glide.with(itemView.context)
                    .load(data.pictureLink)
                    .into(image_list)

                title_list.text = data.name
                desc_list.text = data.description
                source_list.text = data.city
                rating.text = data.rating.toString()

            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }

}
