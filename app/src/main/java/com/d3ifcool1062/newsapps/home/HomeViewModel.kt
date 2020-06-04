package com.d3ifcool1062.newsapps.home

import android.app.Application
import androidx.lifecycle.*
import com.d3ifcool1062.newsapps.database.getDatabase
import com.d3ifcool1062.newsapps.domain.NewsProperty
import com.d3ifcool1062.newsapps.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    //Navigate To Detail
    private val _navigateToDetailNews = MutableLiveData<NewsProperty>()
    val navigateToDetailNews: LiveData<NewsProperty> get() = _navigateToDetailNews

    //Corountine
    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    //Get Data From Room Database
    private val database = getDatabase(application)
    private val newsRepository = NewsRepository(database)

    init {
        coroutineScope.launch {
            newsRepository.addToRoom("")
            newsRepository.addToRoom("technology")
            newsRepository.addToRoom("entertainment")
            newsRepository.addToRoom("business")
            newsRepository.addToRoom("health")
            newsRepository.addToRoom("science")
            newsRepository.addToRoom("sports")
        }
    }

    //Input Data From Room to LiveData(bind to Layout)
    val listNewsTechnology = newsRepository.newsTechnology
    val listNewsGeneral = newsRepository.newsGeneral
    val listEntertain = newsRepository.newsEntertainment
    val listBusiness = newsRepository.newsBusiness
    val listHealth = newsRepository.newsBusiness
    val listScience = newsRepository.newsScience
    val listSport = newsRepository.newsSports

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    //when click item news, they send object news to other fragment
    fun onItemSelected(newsProperty: NewsProperty) {
        _navigateToDetailNews.value = newsProperty
    }

    //After finish to navigate
    fun onFinishItemSelected() {
        _navigateToDetailNews.value = null
    }

    //Factory from ViewModel
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}