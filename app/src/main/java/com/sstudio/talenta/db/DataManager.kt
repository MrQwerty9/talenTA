package com.sstudio.talenta.db

import android.content.Context
import androidx.room.Room

class DataManager(val context: Context) {

    val dbName = "com_sstudio_talenTA.db"

    val resultDatabase: ResultDatabase =
        Room.databaseBuilder(context, ResultDatabase::class.java, dbName).build()
}