package com.alhudaghifari.newstop.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alhudaghifari.newstop.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }
}