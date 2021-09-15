package com.alhudaghifari.newstop.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.alhudaghifari.newstop.data.model.ArticlesItem

@Dao
interface AppDao {
    @Update
    fun updateNews(news: ArticlesItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<ArticlesItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertANews(news: ArticlesItem)

    @Query("SELECT * FROM news_table")
    fun getNews(): DataSource.Factory<Int, ArticlesItem>

    @Query("SELECT * from news_table where newsId = :id")
    fun getANews(id: Int) : ArticlesItem?

    @Query("SELECT * FROM news_table where isFavorite = 1")
    fun getFavoriteNews(): DataSource.Factory<Int, ArticlesItem>
}