package com.example.myapp.model

import com.google.gson.annotations.SerializedName

class MyResponse (
    @SerializedName("response") var response: MatchResponse
)