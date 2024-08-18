package com.example.weather.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JsonPlaceHolderApi{
    @GET("posts")
    fun getPosts(): Call<MutableList<Post>>

    @GET("posts/1/comments")
    fun getComments():Call<MutableList<Comment>>

    @POST("posts")
     fun createPost(@Body post: Post): Call<Post>


     //this is another way of posting data in which all the data is passed as parameter in the call in the viewmodel
     // here  instead of  post.  val call = jsonPlaceHolderApi.createPost(post)

     @FormUrlEncoded
     @POST("posts")
     fun createPost(
         @Field("userId") userId:Int,
         @Field("title") title:String,
         @Field("body") text:String,
     ) :Call<Post>

     //these are used to update some data that is written inside the @Path
     @PUT("posts/{id}")
     fun putPost(@Path("id") id:Int,@Body post:Post):Call<Post>

    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id:Int,@Body post:Post):Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int):Call<Unit>

}