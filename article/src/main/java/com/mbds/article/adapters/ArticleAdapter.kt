package com.mbds.article.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.article.R
import com.mbds.article.model.Article

class ArticleAdapter(private val dataset: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            //nameSource
            val txtSource: TextView = root.findViewById(R.id.source)
            val source = item.source.toString()
            val sourceName = source.substring(source.indexOf("name=") + 5 ,source.length - 1)
            //titre
            val txtName: TextView = root.findViewById(R.id.category_name)
            //date
            val date = toDateFormat(item.publishedAt.toString())


            val txtPublishedAt: TextView = root.findViewById(R.id.published_at)

            txtSource.text = sourceName
            txtName.text = item.title
            txtPublishedAt.text = date

            val imageView = root.findViewById<ImageView>(R.id.category_image)

            Glide
                .with(root)
                .load(item.urlToImage)
                .centerCrop()
                .placeholder(R.drawable.plholder)
                .into(imageView);

        }
        private fun toDateFormat(date: String): String{
            //2020-10-21T13:28:15Z
            val dateReturn = date.split("[-T:Z]".toRegex())
            return "Le "+ dateReturn[2] +"-"+ dateReturn[1] +"-" + dateReturn[0] +" à " + dateReturn[3]+"h"+dateReturn[4]
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