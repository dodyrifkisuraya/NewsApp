package com.d3ifcool1062.newsapps.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.d3ifcool1062.newsapps.domain.NewsProperty

@Entity
class DatabaseNews constructor(
    @PrimaryKey
    val url: String,
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val category : String?
)

//Convert List DatabaseNews to List NewsProperty
fun List<DatabaseNews>.asDomainModel(): List<NewsProperty> {
    return map {
        NewsProperty(
            url = it.url,
            author = it.author,
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content,
            category = it.category
        )
    }
}

