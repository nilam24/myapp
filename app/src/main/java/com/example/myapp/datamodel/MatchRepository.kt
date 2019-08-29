package com.example.myapp.datamodel

import androidx.lifecycle.LiveData
import com.example.myapp.model.Venues

class MatchRepository(private var matchDao: MatchDao) {

    var allList:LiveData<List<Venues>> = matchDao.selectAllRecord()
    var saveList:LiveData<List<Venues>> = matchDao.selectRecord()

    fun insert(venues: Venues){
            matchDao.insertRecord(venues)
    }
    fun update(id:String,enable:Boolean){
        matchDao.updateRecord(id,enable)
    }

    fun deleteRecord(id: String){
        matchDao.delete(id)
    }
}