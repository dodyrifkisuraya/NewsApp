package com.d3ifcool1062.newsapps.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.d3ifcool1062.newsapps.R
import com.d3ifcool1062.newsapps.databinding.ItemGeneralNewsBinding
import com.d3ifcool1062.newsapps.domain.NewsProperty

class GeneralNewsAdapter(val clickListener: NewsClickListener) : RecyclerView.Adapter<GeneralNewsViewHolder>() {

    //Initiialize List NewsProperty
    var news: List<NewsProperty> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralNewsViewHolder {
        //Inflate itemNews for this Adapter
        val withDataBinding: ItemGeneralNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            GeneralNewsViewHolder.LAYOUT,
            parent,
            false
        )
        return GeneralNewsViewHolder(withDataBinding)
    }

    override fun getItemCount() = news.size

    //Bind Item News to each Item layout
    override fun onBindViewHolder(holder: GeneralNewsViewHolder, position: Int) {
        holder.bind.also {
            it.news = news[position]
            it.clickListener = clickListener
        }
    }

}


//Holder layout property from item_news
class GeneralNewsViewHolder(val bind: ItemGeneralNewsBinding) : RecyclerView.ViewHolder(bind.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_general_news
    }
}