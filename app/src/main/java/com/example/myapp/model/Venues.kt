package com.example.myapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "venue")
data class Venues (
    @PrimaryKey(autoGenerate = true) var _id:Long=0,
    @ColumnInfo(name = "id") var id : String="",
    @ColumnInfo(name = "name") var name : String="",
//    @ColumnInfo(name="phone") var phone : String,
 //   @ColumnInfo(name="address") var address:String,
    @ColumnInfo(name="isSaved")var isSaved:Boolean=false


) : Serializable{
    //constructor() : this(0,"","",false)
}

