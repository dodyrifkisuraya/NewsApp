package com.d3ifcool1062.newsapps.network

import com.d3ifcool1062.newsapps.database.DatabaseNews
import com.d3ifcool1062.newsapps.domain.News
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "http://newsapi.org/v2/"
//"http://newsapi.org/v2/top-headlines?country=id&apiKey=f9010cd25f824daf9287b030f1bc5f45"
interface NewsAPIService {
    @GET("top-headlines?country=id&apiKey=f9010cd25f824daf9287b030f1bc5f45")
    fun getPropertyNetwork():
            Deferred<News>

}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


object NewsAPI {
    val retrofitService: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}

