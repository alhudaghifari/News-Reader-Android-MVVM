package com.alhudaghifari.newstop.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alhudaghifari.newstop.data.model.ArticlesItem

@Database(entities = [ArticlesItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}