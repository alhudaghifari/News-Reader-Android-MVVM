package com.alhudaghifari.newstop.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alhudaghifari.newstop.R
import com.alhudaghifari.newstop.data.model.ArticlesItem
import com.alhudaghifari.newstop.databinding.ItemNewsBinding
import com.alhudaghifari.newstop.ui.detail.DetailNewsActivity
import com.alhudaghifari.newstop.utils.TimeUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoriteAdapter : PagedListAdapter<ArticlesItem, FavoriteAdapter.MyViewHolder>(DIFF_CALLBACK)  {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.newsId == newItem.newsId
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val muv = getItem(position)
        if (muv != null) {
            holder.bind(muv)
        }
    }


    class MyViewHolder(private val binding : ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem) {
            with(binding) {
                tvItemTitle.text = data.title
                tvPublished.text = TimeUtils.getDateFormatting(data.publishedAt ?: "")
                tvDescription.text = data.description ?: ""

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailNewsActivity::class.java)
                    intent.putExtra(DetailNewsActivity.NEWS_DATA, data)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}