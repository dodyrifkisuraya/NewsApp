package com.d3ifcool1062.newsapps.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

//Query manage data in Room
@Dao
interface NewsDao {
    @Query("SELECT * FROM databasenews")
    fun getAllNews(): LiveData<List<DatabaseNews>>

    @Query("SELECT * FROM databasenews WHERE category = :categori")
    fun getCategory(categori: String) : LiveData<List<DatabaseNews>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news: DatabaseNews)

    @Query("DELETE FROM databasenews")
    fun deleteAll()

    @Query("DELETE FROM databasenews WHERE category = :categori")
    fun deleteCategory(categori : String)
}

//Initialize Room Database
@Database(entities = [DatabaseNews::class], version = 2)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}

//Create Database, with Singleton Concept
private lateinit var INSTANCE: NewsDatabase

fun getDatabase(context: Context): NewsDatabase {
    synchronized(NewsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news"
            ).build()
        }
    }
    return INSTANCE
}