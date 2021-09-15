package com.alhudaghifari.newstop.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alhudaghifari.newstop.data.NewsRepository
import com.alhudaghifari.newstop.data.model.ArticlesItem
import com.alhudaghifari.newstop.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    fun getNews() : LiveData<Resource<PagedList<ArticlesItem>>> = repository.getNews()
}