package com.alhudaghifari.newstop.data.remote.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alhudaghifari.newstop.api.NewsService
import com.alhudaghifari.newstop.data.model.ArticlesItem
import com.alhudaghifari.newstop.data.model.NewsModel
import com.alhudaghifari.newstop.data.remote.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor
    (private val service: NewsService) {
    fun getNews() : LiveData<ApiResponse<List<ArticlesItem>>> {
        val data = MutableLiveData<ApiResponse<List<ArticlesItem>>>()
        service.getDetailMovie().enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        val list = result.articles
                        if (list != null)
                            data.postValue(ApiResponse.success(list))
                        else
                            data.postValue(ApiResponse.empty(response.message() ?: "Error Happen a", mutableListOf()))
                    } else {
                        data.postValue(ApiResponse.empty(response.message() ?: "Error Happen b", mutableListOf()))
                    }
                } else {
                    data.postValue(ApiResponse.error(response.message() ?: "Error Happen c", mutableListOf()))
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                data.postValue(ApiResponse.error(t.message.toString(), mutableListOf()))
            }

        })
        return data
    }
}