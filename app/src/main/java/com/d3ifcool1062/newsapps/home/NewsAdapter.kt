package com.d3ifcool1062.newsapps.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.d3ifcool1062.newsapps.R
import com.d3ifcool1062.newsapps.databinding.ItemNewsBinding
import com.d3ifcool1062.newsapps.domain.NewsProperty

class NewsAdapter(val clickListener: NewsClickListener) : RecyclerView.Adapter<NewsViewHolder>() {

    //Initiialize List NewsProperty
    var news: List<NewsProperty> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //Inflate itemNews for this Adapter
        val withDataBinding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NewsViewHolder.LAYOUT,
            parent,
            false
        )
        return NewsViewHolder(withDataBinding)
    }

    override fun getItemCount() = news.size

    //Bind Item News to each Item layout
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind.also {
            it.news = news[position]
            it.clickListener = clickListener
        }
    }

}

//Click Listener for passing data news
class NewsClickListener(val clickListener: (news: NewsProperty) -> Unit) {
    fun onClick(news: NewsProperty) = clickListener(news)
}


//Holder layout property from item_layout
class NewsViewHolder(val bind: ItemNewsBinding) : RecyclerView.ViewHolder(bind.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_news
    }
}