package com.example.myapp.model

import com.example.myapp.model.Venues
import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("venues") var venues : List<Venues>
)
