package com.d3ifcool1062.newsapps.domain

import android.os.Parcelable
import androidx.lifecycle.Transformations.map
import com.d3ifcool1062.newsapps.database.DatabaseNews
import kotlinx.android.parcel.Parcelize

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsProperty>
)

@Parcelize
data class NewsProperty(
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) : Parcelable

@Parcelize
data class Source(val id: Int?, val name: String) : Parcelable

fun NewsProperty.asDatabaseModel() : DatabaseNews{
    return DatabaseNews(
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}
