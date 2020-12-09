package com.example.simpleappmvvm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "Note_Table")
data class Note  (

    @PrimaryKey(autoGenerate = true) val _id:Int,
    var title:String,
    var description:String,
    var priority: String


    ) {
}






