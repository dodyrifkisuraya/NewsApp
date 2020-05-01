package com.d3ifcool1062.newsapps

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String){
    Glide.with(imageView.context).load(url).into(imageView)
}