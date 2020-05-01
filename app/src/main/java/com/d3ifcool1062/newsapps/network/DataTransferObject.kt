package com.d3ifcool1062.newsapps.network

import com.d3ifcool1062.newsapps.database.DatabaseNews
import com.d3ifcool1062.newsapps.domain.Source
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkNewsContainer(val news: List<NetworkNews>)


@JsonClass(generateAdapter = true)
data class NetworkNews(
    val url : String,
    val author : String,
    val title : String,
    val description : String,
    val urlToImage : String,
    val publishedAt : String,
    val content : String
)


fun NetworkNewsContainer.asDatabaseModel() : Array<DatabaseNews>{
    return news.map {
        DatabaseNews(
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content
        )
    }.toTypedArray()
}