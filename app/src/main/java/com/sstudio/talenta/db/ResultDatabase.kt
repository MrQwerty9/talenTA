package com.sstudio.talenta.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sstudio.talenta.model.Result

@Database(entities = [Result::class], version = 1)
abstract class ResultDatabase: RoomDatabase() {
    abstract fun getResultDao(): ResultDao
}