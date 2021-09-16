package com.alhudaghifari.newstop.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alhudaghifari.newstop.data.local.NewsLocalDataSource
import com.alhudaghifari.newstop.data.model.ArticlesItem
import com.alhudaghifari.newstop.data.remote.ApiResponse
import com.alhudaghifari.newstop.data.remote.source.NewsRemoteDataSource
import com.alhudaghifari.newstop.utils.AppExecutors
import com.alhudaghifari.newstop.vo.Resource
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource,
    private val appExecutors: AppExecutors
) {
    fun getNews() : LiveData<Resource<PagedList<ArticlesItem>>> {
        return object : NetworkBoundResource<PagedList<ArticlesItem>, List<ArticlesItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<ArticlesItem>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllNews(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<ArticlesItem>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ArticlesItem>>> = remoteDataSource.getNews()

            override fun saveCallResult(data: List<ArticlesItem>) = localDataSource.insertNews(data)
        }.asLiveData()
    }

    fun getFavoriteNews(): LiveData<PagedList<ArticlesItem>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteNews(), config).build()
    }

    fun getAFavoriteNews(idNews: Int) : LiveData<ArticlesItem> {
        val data = MutableLiveData<ArticlesItem>()
        appExecutors.diskIO().execute {
            data.postValue(localDataSource.getANews(idNews))
        }
        return data
    }

    fun setFavoriteNews(news: ArticlesItem, isFavorite: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(news, isFavorite) }
    }

    fun doLogin(username: String, password: String) : Boolean {
        if (username == "agit" && password == "123") return true
        else return false
    }
}