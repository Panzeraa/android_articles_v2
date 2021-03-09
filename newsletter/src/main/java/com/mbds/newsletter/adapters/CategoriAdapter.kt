package com.mbds.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.model.Category

class CategoriAdapter(private val dataset: List<Category>) :
    RecyclerView.Adapter<CategoriAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Category) {
            val txtName: TextView = root.findViewById<TextView>(R.id.category_name)
            txtName.text = item.name

            val imageView = root.findViewById<ImageView>(R.id.category_image)

            Glide
                .with(root)
                .load(item.image)
                .fitCenter()
                .placeholder(R.drawable.plholder)
                .into(imageView);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

//    override fun getItemCount(): Int { return dataset.size}
    override fun getItemCount(): Int =  dataset.size


}