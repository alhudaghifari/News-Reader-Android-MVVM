package com.alhudaghifari.newstop.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alhudaghifari.newstop.data.NewsRepository
import com.alhudaghifari.newstop.data.model.ArticlesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailNewsViewModel @Inject constructor(private val repository: NewsRepository)
    : ViewModel() {
    fun setFavoriteNews(news: ArticlesItem, isFavorite: Boolean) = repository.setFavoriteNews(news, isFavorite)

    fun getAFavNews(idNews: Int) : LiveData<ArticlesItem> = repository.getAFavoriteNews(idNews)
}