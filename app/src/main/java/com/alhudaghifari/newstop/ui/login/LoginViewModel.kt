package com.alhudaghifari.newstop.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alhudaghifari.newstop.data.NewsRepository
import com.alhudaghifari.newstop.utils.SharedPrefManager
import com.alhudaghifari.newstop.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val sharedPrefManager: SharedPrefManager,
) : ViewModel() {

    private val _isLogged = MutableLiveData<Resource<Boolean>>()
    val isLogged : LiveData<Resource<Boolean>> = _isLogged

    fun doLogin(username: String, password: String) {
        _isLogged.postValue(Resource.loading(null))
        if (repository.doLogin(username, password)) {
            sharedPrefManager.setLogin(true)
            _isLogged.postValue(Resource.success(true))
        } else {
            _isLogged.postValue(Resource.error("wrong credential (use data in hint to login)", false))
        }
    }
}