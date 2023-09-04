package com.sto_opka91.newsapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sto_opka91.newsapp.R
import com.sto_opka91.newsapp.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view:View): RecyclerView.ViewHolder(view)
    private val callback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem==newItem
        }
    }
    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            val tvArticleTitle = findViewById<TextView>(R.id.tvArticleTitle)
            val ivArticleImage = findViewById<ImageView>(R.id.ivArticleImage)
            val tvArticleDate = findViewById<TextView>(R.id.tvArticleDate)
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            Log.d("UrlIm", "Url: ${article}")
            ivArticleImage.clipToOutline = true
            tvArticleTitle.text = article.title
            tvArticleDate.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let{it(article)}
            }
        }

    }
    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnClickListener (listener : (Article) -> Unit){
        onItemClickListener = listener
    }
}