package com.example.weather

import androidx.compose.runtime.livedata.observeAsState

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.viewmodel.PostViewModel



@Composable
fun CreatePostScreen(viewModel: PostViewModel) {
    val createdPost by viewModel.createdPost.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.createPost()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Created Post:")
        Spacer(modifier = Modifier.height(8.dp))
        createdPost?.let { post ->
            Text(text = "User ID: ${post.userId}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Title: ${post.title}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Text: ${post.text}")
        } ?: run {
            Text(text = "Creating post...")
        }
    }
}
