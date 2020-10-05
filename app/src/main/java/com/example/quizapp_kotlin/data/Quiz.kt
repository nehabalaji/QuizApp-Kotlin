package com.example.quizapp_kotlin.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    var id: Long,

    @ColumnInfo(name = "StateName")
    var stateName: String,

    @ColumnInfo(name = "CapitalName")
    var capitalName: String
): Parcelable