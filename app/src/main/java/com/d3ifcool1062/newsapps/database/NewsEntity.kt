package com.d3ifcool1062.newsapps.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.d3ifcool1062.newsapps.domain.News
import com.d3ifcool1062.newsapps.domain.NewsProperty
import com.d3ifcool1062.newsapps.domain.Source

@Entity
class DatabaseNews constructor(
    @PrimaryKey
    val url : String,
    val author : String?,
    val title : String,
    val description : String?,
    val urlToImage : String?,
    val publishedAt : String,
    val content : String?
)

fun List<DatabaseNews>.asDomainModel() : List<NewsProperty>{
    return map {
        NewsProperty(
            url = it.url,
            author = it.author,
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content
        )
    }
}

fun List<DatabaseNews>.asDatabaseModel(): List<DatabaseNews>{
    return map {
        DatabaseNews(
            url = it.url,
            author = it.author,
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content
        )
    }
}


//@Entity
//class ResultNews constructor(
//    @PrimaryKey
//    val status: String,
//    val totalResults: Int,
//    val articles: List<NewsProperty>
//)
//
//fun List<ResultNews>.asDomainModel() : List<News>{
//    return map{
//        News(
//            totalResults = it.totalResults,
//            articles = it.articles,
//            status = it.status
//        )
//    }
//}
//
//fun List<ResultNews>.asDBModel(): List<ResultNews>{
//    return map {
//        ResultNews(
//            status = it.status,
//            totalResults = it.totalResults,
//            articles = it.articles
//        )
//    }
//}

