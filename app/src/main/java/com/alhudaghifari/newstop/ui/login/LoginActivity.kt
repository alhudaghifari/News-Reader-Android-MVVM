package com.alhudaghifari.newstop.ui.login

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.alhudaghifari.newstop.R
import com.alhudaghifari.newstop.databinding.ActivityLoginBinding
import com.alhudaghifari.newstop.ui.main.MainActivity
import com.alhudaghifari.newstop.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    private var isCalled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnLogin.setOnClickListener {
                if (etName.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()) {
                    Toast.makeText(this@LoginActivity, resources.getString(R.string.credential_false), Toast.LENGTH_LONG).show()
                } else {
                    viewModel.doLogin(etName.text.toString().trim(), etPassword.text.toString().trim())
                    observeLogin()
                }
            }
        }
    }

    private fun observeLogin() {
        viewModel.isLogged.observe(this, {
            if (it != null) {
                when(it.status) {
                    Status.LOADING -> {
                        showLoading()
                    }
                    Status.SUCCESS -> {
                        hideLoading()
                        it.data?.let {
                            if (it) {
                                if (!isCalled) {
                                    isCalled = true
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        hideLoading()
                        if (it.data ?: true == false)
                            Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun showLoading() {
        binding.tvLoading.visibility = View.VISIBLE
        binding.btnLogin.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.tvLoading.visibility = View.GONE
        binding.btnLogin.visibility = View.VISIBLE
    }
}