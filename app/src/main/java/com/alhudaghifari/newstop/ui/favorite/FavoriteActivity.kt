package com.alhudaghifari.newstop.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alhudaghifari.newstop.R
import com.alhudaghifari.newstop.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favAdapter: FavoriteAdapter
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favAdapter = FavoriteAdapter()
        with(binding.rvData) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favAdapter
        }
        observeData()
    }

    private fun observeData() {
        viewModel.getFavoriteNews().observe(this, {
            if (it != null) {
                showDataList()
                favAdapter.submitList(it)
            } else {
                showNoData()
            }
        })
    }

    private fun showNoData() {
        binding.rvData.visibility = View.GONE
        binding.tvNoData.visibility = View.VISIBLE
    }

    private fun showDataList() {
        binding.rvData.visibility = View.VISIBLE
        binding.tvNoData.visibility = View.GONE
    }
}