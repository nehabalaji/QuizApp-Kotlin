package com.example.quizapp_kotlin.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "StateAndCapital")
data class Quiz(

    @ColumnInfo(name = "StateName")
    var stateName: String,

    @ColumnInfo(name = "CapitalName")
    var capitalName: String,

    @PrimaryKey
    @ColumnInfo(name = "ID")
    var id: Long = 0L
): Parcelable