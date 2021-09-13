package com.alhudaghifari.newstop.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsModel(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class Source(

	@field:SerializedName("name")
	@ColumnInfo(name = "name")
	val name: String? = null,

	@field:SerializedName("id")
	@ColumnInfo(name = "idSource")
	val idSource: String? = null
) : Parcelable

@Parcelize
@Entity(tableName = "news_table")
data class ArticlesItem(
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "newsId")
	var newsId: String,

	@ColumnInfo(name = "isFavorite")
	var isFavorite: Boolean = false,

	@field:SerializedName("publishedAt")
	@ColumnInfo(name = "publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	@ColumnInfo(name = "author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	@ColumnInfo(name = "urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	@ColumnInfo(name = "description")
	val description: String? = null,

	@field:SerializedName("source")
	@Embedded
	val source: Source? = null,

	@field:SerializedName("title")
	@ColumnInfo(name = "title")
	val title: String? = null,

	@field:SerializedName("url")
	@ColumnInfo(name = "url")
	val url: String? = null,

	@field:SerializedName("content")
	@ColumnInfo(name = "content")
	val content: String? = null
) : Parcelable
