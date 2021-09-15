package com.alhudaghifari.newstop.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alhudaghifari.newstop.data.NewsRepository
import com.alhudaghifari.newstop.data.model.ArticlesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: NewsRepository)
    : ViewModel() {
        fun getFavoriteNews() : LiveData<PagedList<ArticlesItem>> = repository.getFavoriteNews()
}