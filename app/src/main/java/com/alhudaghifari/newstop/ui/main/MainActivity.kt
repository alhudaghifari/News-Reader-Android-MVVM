package com.alhudaghifari.newstop.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alhudaghifari.newstop.R
import com.alhudaghifari.newstop.databinding.ActivityMainBinding
import com.alhudaghifari.newstop.ui.favorite.FavoriteActivity
import com.alhudaghifari.newstop.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    private lateinit var mainAdapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainAdapter = MainAdapter()
        with(binding.rvData) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
        observeData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            startActivity(Intent(this, FavoriteActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeData() {
        viewModel.getNews().observe(this, {
            if (it != null) {
                when(it.status) {
                    Status.LOADING -> {
                        showLoading()
                    }
                    Status.SUCCESS -> {
                        hideLoading()
                        it.data?.let {
                            showDataList()
                            mainAdapter.submitList(it)
                        }
                    }
                    Status.ERROR -> {
                        hideLoading()
                        showNoData()
                    }
                }
            }
        })
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvData.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.rvData.visibility = View.VISIBLE
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