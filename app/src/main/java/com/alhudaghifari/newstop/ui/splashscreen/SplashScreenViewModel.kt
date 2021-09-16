package com.alhudaghifari.newstop.ui.splashscreen

import androidx.lifecycle.ViewModel
import com.alhudaghifari.newstop.utils.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
) : ViewModel() {
    fun isLogged() : Boolean = sharedPrefManager.isLogin()
}