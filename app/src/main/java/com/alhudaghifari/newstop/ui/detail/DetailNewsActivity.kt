package com.alhudaghifari.newstop.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alhudaghifari.newstop.R
import com.alhudaghifari.newstop.data.model.ArticlesItem
import com.alhudaghifari.newstop.databinding.ActivityDetailNewsBinding
import com.alhudaghifari.newstop.ui.detailwebview.DetailWebviewActivity
import com.alhudaghifari.newstop.utils.TimeUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNewsActivity : AppCompatActivity() {

    companion object {
        const val NEWS_DATA = "news_data"
    }

    private lateinit var binding: ActivityDetailNewsBinding
    private lateinit var dataItem : ArticlesItem
    private val viewModel : DetailNewsViewModel by viewModels()
    private var isFav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val data = extras.getParcelable<ArticlesItem>(NEWS_DATA)
            data?.let {
                dataItem = it
                setData()
                observeFavorite()
            }
        }

        setFavoriteButtonListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteButtonListener() {
        binding.ibFavorite.setOnClickListener {
            isFav = !isFav
            viewModel.setFavoriteNews(dataItem, isFav)
            setColorFav()
        }
    }

    private fun observeFavorite() {
        viewModel.getAFavNews(dataItem.newsId).observe(this, {
            if (it != null) {
                it.isFavorite.let {
                    isFav = it
                    setColorFav()
                }
            }
        })
    }

    private fun setColorFav() {
        if (isFav) {
            binding.ibFavorite.setImageResource(R.drawable.ic_favorite_full_red)
        } else {
            binding.ibFavorite.setImageResource(R.drawable.ic_favorite_border_red)
        }
    }

    private fun setData() {
        with(binding) {
            tvTitle.text = dataItem.title
            tvPublished.text = TimeUtils.getDateFormatting(dataItem.publishedAt ?: "")
            tvAuthor.text = dataItem.author
            tvSource.text = dataItem.source?.name ?: "-"
            tvOverview.text = dataItem.content
            tvReadFull.setOnClickListener {
                val intent = Intent(this@DetailNewsActivity, DetailWebviewActivity::class.java)
                intent.putExtra(DetailWebviewActivity.data_url, dataItem.url)
                startActivity(intent)
            }

            Glide.with(this@DetailNewsActivity)
                .load(dataItem.urlToImage)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(ivImageDetail)
        }
    }
}