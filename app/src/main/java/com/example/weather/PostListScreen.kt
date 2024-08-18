package com.example.weather


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.model.Post
@Composable
fun PostListScreen(posts: List<Post>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 32.dp, start = 16.dp, end = 16.dp)) {
        items(posts) { post ->
            PostItem(post)
        }
    }
}
@Composable
fun PostItem(post: Post) {
    Column {
        Text(text = "ID: ${post.id}")
        Text(text = "User ID: ${post.userId}")
        Text(text = "Title: ${post.title}")
        Text(text = "Text: ${post.text}")
    }
}
