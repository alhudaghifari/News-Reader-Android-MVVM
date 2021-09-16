package com.alhudaghifari.newstop.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefManager @Inject constructor(@ApplicationContext context : Context) {
    private lateinit var editor: SharedPreferences.Editor
    private val PREF_NAME = "MyAppsNewsT0p"
    var prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val key_login = "l0g1nKey"

    fun isLogin() : Boolean {
        return prefs.getBoolean(key_login, false)
    }

    fun setLogin(isLogin: Boolean) {
        prefs.edit().putBoolean(key_login, isLogin).apply()
    }
}