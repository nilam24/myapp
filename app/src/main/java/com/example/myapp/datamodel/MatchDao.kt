package com.example.myapp.datamodel

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapp.model.Venues

@Dao
interface MatchDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(venues: Venues)

    @Query("Update venue SET isSaved=:enable  where id=:id" )
    fun updateRecord(id:String,enable:Boolean)

    @Query("Select * From venue")
    fun selectAllRecord():LiveData<List<Venues>>

    @Query("Select * From venue where isSaved")
    fun selectRecord():LiveData<List<Venues>>

    @Query("Delete  From venue where id=:id")
    //@Delete
    fun delete(id: String)


}