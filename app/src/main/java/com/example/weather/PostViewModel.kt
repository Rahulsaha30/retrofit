package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Comment
import com.example.weather.model.JsonPlaceHolderApi
import com.example.weather.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel : ViewModel() {

  private  val _posts = MutableLiveData<List<Post>?>()
    val posts: LiveData<List<Post>?> = _posts


    private val _comments = MutableLiveData<List<Comment>?>()
    val comments: LiveData<List<Comment>?> =_comments


    private val _createdPost = MutableLiveData<Post?>()
    val createdPost: LiveData<Post?> = _createdPost


    fun fetchPosts() {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val call = jsonPlaceHolderApi.getPosts()

        call.enqueue(object : Callback<MutableList<Post>> {
            override fun onResponse(call: Call<MutableList<Post>>, response: Response<MutableList<Post>>) {
                if (response.isSuccessful) {
                    val fetchedPosts = response.body()
                    Log.d("PostViewModel", "Posts fetched successfully: ${fetchedPosts?.size} items")
                    _posts.postValue(fetchedPosts)
                } else {
                    Log.e("PostViewModel", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
                Log.e("PostViewModel", "Error: ${t.message}")
            }
        })
    }

    fun fetchComments() {



        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val call = jsonPlaceHolderApi.getComments()

        call.enqueue(object : Callback<MutableList<Comment>> {
            override fun onResponse(call: Call<MutableList<Comment>>, response: Response<MutableList<Comment>>) {
                if (response.isSuccessful) {
                    val fetchedComments = response.body()
                    Log.d("PostViewModel", "Comments fetched successfully: ${fetchedComments?.size} items")
                    Log.d("PostViewModel", "Fetched Comments: $fetchedComments")
                    _comments.postValue(fetchedComments)
                } else {
                    Log.e("PostViewModel", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MutableList<Comment>>, t: Throwable) {
                Log.e("PostViewModel", "Error: ${t.message}")
            }
        })
    }

    fun createPost() {


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val post = Post(userId = 20, title = "New title", text = "New text")
        val call = jsonPlaceHolderApi.createPost(43,"New title ","new text")
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    _createdPost.postValue(response.body())
                    Log.d("PostViewModel", "Post created: ${response.body()}")
                } else {
                    Log.e("PostViewModel", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("PostViewModel", "Error: ${t.message}")
            }
        })
    }

   fun updatePost(){


       val retrofit = Retrofit.Builder()
           .baseUrl("https://jsonplaceholder.typicode.com/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()

       val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val post=Post(25,null,"how are you")

       val call=jsonPlaceHolderApi.putPost(5,post)

       call.enqueue(object : Callback<Post> {
           override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
               if (response != null) {
                   if (response.isSuccessful) {
                       _createdPost.postValue(response.body())
                       Log.d("PostViewModel", "Post created: ${response.body()}")
                   } else {
                       Log.e("PostViewModel", "Response unsuccessful: ${response.code()}")
                   }
               }
           }

           override fun onFailure(call: Call<Post>, t: Throwable) {
               Log.e("PostViewModel", "Error: ${t.message}")
           }

       })
    }

    fun deletePost(){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call=jsonPlaceHolderApi.deletePost(25)

        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

                if (response.isSuccessful) {
                    Log.d("PostViewModel", "Post deleted successfully: ${response.code()}")
                } else {
                    Log.e("PostViewModel", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("PostViewModel", "Error: ${t.message}")
            }

        })
    }
}