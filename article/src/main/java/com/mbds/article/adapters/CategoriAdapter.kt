package com.mbds.article.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.article.MainActivity
import com.mbds.article.R
import com.mbds.article.changeFragment
import com.mbds.article.fragments.ArticlesFragment
import com.mbds.article.model.Category

class CategoriAdapter(private val dataset: List<Category>, private val mOnCategorieListener: OnCategorieListener) :
        RecyclerView.Adapter<CategoriAdapter.ViewHolder>() {


    class ViewHolder(val root: View, val onCategorieListener: OnCategorieListener) : RecyclerView.ViewHolder(root), View.OnClickListener {
        fun bind(item: Category) {
            val txtName = root.findViewById<TextView>(R.id.category_name)
            val imageView = root.findViewById<ImageView>(R.id.category_image)
            val txtDescription = root.findViewById<TextView>(R.id.category_description)
            txtName.text = item.name
            txtDescription.text = item.description
            Glide
                    .with(root)
                    .load(item.image)
                    .fitCenter()
                    .placeholder(R.drawable.plholder)
                    .into(imageView);
            root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onCategorieListener.onCategorieClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val rootView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_categories, parent, false)
        return ViewHolder(rootView, mOnCategorieListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
                dataset[position]
        )
    }

    override fun getItemCount(): Int = dataset.size

    interface OnCategorieListener{
        fun onCategorieClick(position: Int)
    }
}