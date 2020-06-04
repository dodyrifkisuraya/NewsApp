package com.d3ifcool1062.newsapps

import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?){
    Glide.with(imageView.context)
        .load(url)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_broken_image_24).error(R.drawable.ic_baseline_broken_image_24))
        .into(imageView)
}
