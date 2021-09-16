package com.alhudaghifari.newstop.ui.detailwebview

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.alhudaghifari.newstop.databinding.ActivityDetailWebviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailWebviewActivity : AppCompatActivity() {

    companion object {
        const val data_url = "DATA_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val url = extras.getString(data_url, "")
            binding.webview.webViewClient =  object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return false
                }
            }
            binding.webview.loadUrl(url)
        }
    }
}