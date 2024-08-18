package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class Comment(
    val PostId:Int,
    val id:Int,
    val name:String,
    val email:String,
  @SerializedName("body")  val text:String
)