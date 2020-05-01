package com.d3ifcool1062.newsapps.home

import android.app.Application
import androidx.lifecycle.*
import com.d3ifcool1062.newsapps.domain.NewsProperty
import com.d3ifcool1062.newsapps.repository.NewsRepository
import com.d3ifcool1062.newsapps.database.getDatabase
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    private val database = getDatabase(application)
    private val newsRepository = NewsRepository(database)

    init {
        coroutineScope.launch {
            newsRepository.refreshNews()
        }
    }

    val listNews = newsRepository.news

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

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