package com.d3ifcool1062.newsapps.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao{
    @Query("SELECT * FROM databasenews")
    fun getNews() : LiveData<List<DatabaseNews>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news : DatabaseNews)

    @Query("DELETE FROM databasenews")
    fun deleteAll()
}

@Database(entities = [DatabaseNews::class], version = 2)
abstract class NewsDatabase : RoomDatabase(){
    abstract val newsDao : NewsDao
}

private lateinit var INSTANCE : NewsDatabase

fun getDatabase(context: Context): NewsDatabase{
    synchronized(NewsDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news"
            ).build()
        }
    }
    return INSTANCE
}