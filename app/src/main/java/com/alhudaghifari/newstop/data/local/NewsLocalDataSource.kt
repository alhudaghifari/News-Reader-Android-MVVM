package com.alhudaghifari.newstop.data.local

import androidx.paging.DataSource
import com.alhudaghifari.newstop.data.local.room.AppDao
import com.alhudaghifari.newstop.data.model.ArticlesItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsLocalDataSource @Inject constructor(private val dao: AppDao) {

    fun getAllNews() : DataSource.Factory<Int, ArticlesItem> =
        dao.getNews()

    fun getFavoriteNews() : DataSource.Factory<Int, ArticlesItem> =
        dao.getFavoriteNews()

    fun getANews(idNews: Int) : ArticlesItem? = dao.getANews(idNews)

    fun insertNews(news: List<ArticlesItem>) = dao.insertNews(news)

    fun insertANews(news: ArticlesItem) = dao.insertANews(news)

    fun setFavoriteNews(news: ArticlesItem, isFavorite: Boolean) {
        news.isFavorite = isFavorite
        dao.updateNews(news)
    }
}