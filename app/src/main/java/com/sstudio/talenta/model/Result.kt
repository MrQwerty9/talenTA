package com.sstudio.talenta.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "result")
class Result(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var noTest: Long?,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "placeDateBirth")
    var placeDateBirth: String? = "",
    @ColumnInfo(name = "address")
    var address: String?,
    @ColumnInfo(name = "study")
    var study: String?,
    @ColumnInfo(name = "purposeTest")
    var purposeTest: String?,
    @ColumnInfo(name = "potencyValue")
    var potencyValue: DoubleArray?,
    @ColumnInfo(name = "result")
    var finalResult: Array<Double>?
    ): Parcelable