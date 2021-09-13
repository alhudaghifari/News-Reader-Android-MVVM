package com.alhudaghifari.newstop.api


import com.alhudaghifari.newstop.BuildConfig
import com.alhudaghifari.newstop.data.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {
    @GET("v2/everything")
    fun getDetailMovie(
        @Query("q") query: String = "Indonesia",
        @Query("apiKey") key: String = BuildConfig.k
    ): Call<NewsModel>

    companion object {
        fun create(): NewsService {
            val api = ApiConfig.create()
            return api.create(NewsService::class.java)
        }
    }
}