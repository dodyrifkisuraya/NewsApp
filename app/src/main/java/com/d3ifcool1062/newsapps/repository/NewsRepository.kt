package com.d3ifcool1062.newsapps.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.d3ifcool1062.newsapps.BuildConfig
import com.d3ifcool1062.newsapps.database.NewsDatabase
import com.d3ifcool1062.newsapps.database.asDomainModel
import com.d3ifcool1062.newsapps.domain.News
import com.d3ifcool1062.newsapps.domain.NewsProperty
import com.d3ifcool1062.newsapps.domain.asDatabaseModel
import com.d3ifcool1062.newsapps.network.NewsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(private val database: NewsDatabase) {

    //Variabel to save LiveData<List<NewsProperty>>
    //Main Data
    val newsAll: LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getAllNews()
    ) {
        it.asDomainModel()
    }

    val newsTechnology : LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getCategory("technology")
    ){
        it.asDomainModel()
    }

    val newsGeneral : LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getCategory("general")
    ){
        it.asDomainModel()
    }

    val newsSports : LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getCategory("sports")
    ){
        it.asDomainModel()
    }
    val newsScience : LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getCategory("science")
    ){
        it.asDomainModel()
    }
    val newsHealth : LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getCategory("health")
    ){
        it.asDomainModel()
    }
    val newsEntertainment : LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getCategory("entertainment")
    ){
        it.asDomainModel()
    }
    val newsBusiness : LiveData<List<NewsProperty>> = Transformations.map(
        database.newsDao.getCategory("business")
    ){
        it.asDomainModel()
    }



    //Refresh Room Database
    suspend fun addToRoom(categori : String) {
        withContext(Dispatchers.IO) {
            val dataListNews = NewsAPI.retrofitService.getPropertyNetwork(BuildConfig.API_KEY, categori).await()
            if (categori == ""){
                database.newsDao.deleteCategory("general")
            }else{
                database.newsDao.deleteCategory(categori)
            }
            for (item in dataListNews.articles) {
                if (categori == ""){
                    item.category = "general"
                }else{
                    item.category = categori
                }
                database.newsDao.insertAll(item.asDatabaseModel())
            }
        }
    }


    suspend fun resetDataRoom(){
        withContext(Dispatchers.IO){
            database.newsDao.deleteAll()
        }
    }
}