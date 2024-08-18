package com.example.weather


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.model.Comment


@Composable
fun CommentsScreen(comments: List<Comment>)  {

    if (comments.isEmpty()) {
        Text("No comments available")
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 32.dp, start = 16.dp, end = 16.dp)) {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Text(text = "ID: ${comment.id}")
    Text(text = "Post ID: ${comment.PostId}")
    Text(text = "Name: ${comment.name}")
    Text(text = "Email: ${comment.email}")
    Text(text = "Text: ${comment.text}")
}
