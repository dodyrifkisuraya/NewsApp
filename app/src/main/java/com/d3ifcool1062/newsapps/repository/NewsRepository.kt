package com.d3ifcool1062.newsapps.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.d3ifcool1062.newsapps.database.NewsDatabase
import com.d3ifcool1062.newsapps.database.asDomainModel
import com.d3ifcool1062.newsapps.domain.NewsProperty
import com.d3ifcool1062.newsapps.domain.asDatabaseModel
import com.d3ifcool1062.newsapps.network.NewsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val database: NewsDatabase) {

    val news: LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getNews()
    ) {
        it.asDomainModel()
    }

    suspend fun refreshNews() {
        withContext(Dispatchers.IO) {
            val dataListNews = NewsAPI.retrofitService.getPropertyNetwork().await()

            for (item in dataListNews.articles){
                database.newsDao.insertAll(item.asDatabaseModel())
            }

        }
    }
}