package com.jetbrains.tv.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.jetbrains.tv.data.model.GenreTypes
import com.jetbrains.tv.data.model.Movie
import com.jetbrains.tv.extension.getDrawableIdByName


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieCard(
    movie: Movie,
    isFocused: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val progress = remember { mutableStateOf(movie.watchProgress) }
    val showTrailer = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .defaultMinSize(minHeight = 100.dp)
            .aspectRatio(5f / 3f)
            .graphicsLayer(
                scaleX = if (isFocused) 1.1f else 1f,
                scaleY = if (isFocused) 1.1f else 1f
            )
            .onFocusChanged { state ->
                showTrailer.value = state.isFocused
            },
        onClick = onClick
    ) {
        Box(Modifier.fillMaxSize()) {
            val drawableId = LocalContext.current.getDrawableIdByName(movie.thumbnail)
            Image(
                painter = painterResource(drawableId),
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay
            val brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Black), 0.75f)
            Text(
                text = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(brush)
                    .padding(4.dp),
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )

            // Watch progress indicator
            LinearProgressIndicator(
                progress = progress.value,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(4.dp),
                color = MaterialTheme.colorScheme.secondary
            )

            // Trailer preview overlay
            if (showTrailer.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Trailer Preview",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
