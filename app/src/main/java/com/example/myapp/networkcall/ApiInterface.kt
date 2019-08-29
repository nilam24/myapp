package com.example.myapp.networkcall

import com.example.myapp.model.MyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //"ll=40.7484,-73.9857&oauth_token=NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ&v=20180616"
    @GET("https://api.foursquare.com/v2/venues/search")
    fun getDetailMatch(@Query("ll")ll:String,@Query("oauth_token")oauth_token:String,@Query("v")v:String) : Call<MyResponse>

//    @GET("")
//    Call<> getAllMatch(){@Query() }
//
//    @GET("")
//    Call<> saveMatch()

}