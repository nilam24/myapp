package com.example.myapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapp.datamodel.MatchDao
import com.example.myapp.datamodel.MatchDatabase
import com.example.myapp.datamodel.MatchRepository
import com.example.myapp.model.MyResponse
import com.example.myapp.model.Venues
import com.example.myapp.networkcall.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchViewModel(application: Application):AndroidViewModel(application) {

   // var matchRes : MutableLiveData<MyResponse>?=null
    lateinit var matchRepo : MatchRepository
    lateinit var matchDao:MatchDao
    var allMatches : LiveData<List<Venues>>
    lateinit var saveMatches:LiveData<List<Venues>>

    init {

        var db=MatchDatabase.getInstance(application)
        matchDao=db.matchdao()
        matchRepo= MatchRepository(matchDao)
        allMatches = matchRepo.allList
        saveMatches=matchRepo.saveList

    }

//    fun getMatchDetailList(ll: String,oauth_token: String,v: String):LiveData<MyResponse>{
//
//        if(matchRes==null){
//            matchRes= MutableLiveData()
//            getDetail(ll,oauth_token,v)
//
//
//        }
//
//        return matchRes as MutableLiveData<MyResponse>
//
//    }

    fun getDetail(ll:String,oauth_token:String,v:String){

        var call = ApiClient.getApiClient().getDetailMatch(ll,oauth_token,v)
        call.enqueue(object : Callback<MyResponse>{
            override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {

                Log.e("","failurerespone"+t)
            }

            override fun onResponse(call: Call<MyResponse>?, response: Response<MyResponse>?) {

                if(response!!.isSuccessful){
                    var match= response.body()
                    //matchRes!!.value=response.body()

                    var res=match.response
                    var list = res.venues
                    for(i in 0 until list.size){
                        var ven=list[i]
                        if(allMatches.value!!.isEmpty()) {
                            insertPlace(ven)
                        }
                        else{
                            Log.e("","notEmpty")
                        }
                        Log.e("responsesuccess",""+ven)
                    }

                }
            }
        })
    }

    fun insertPlace(venues: Venues){
        CoroutineScope(Dispatchers.IO).launch {
            matchRepo.insert(venues)
           // matchDao.insertRecord(venues)

        }
    }

    fun updatePlace(id:String,enable:Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            matchRepo.update(id,enable)
           // matchDao.updateRecord(id,enable)
        }
    }

    fun deletePlace(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            matchRepo.deleteRecord(id)
        }
    }

}