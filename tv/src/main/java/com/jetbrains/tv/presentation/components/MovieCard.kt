package com.jetbrains.tv.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .defaultMinSize(minHeight = 100.dp)
            .aspectRatio(5f / 3f)
            .focusRequester(focusRequester)
            .focusable()
            .graphicsLayer(
                scaleX = if (isFocused.value) 1.1f else 1f,
                scaleY = if (isFocused.value) 1.1f else 1f,
            )
            .onFocusChanged { state ->
                isFocused.value = state.isFocused
            }
           ,
        onClick = onClick
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .semantics {
                    contentDescription = movie.title
                }
        ) {
            val drawableId = LocalContext.current.getDrawableIdByName(movie.thumbnail)
            Image(
                painter = painterResource(drawableId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(8.dp),
                contentScale = ContentScale.Crop
            )

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
        }
    }
}

@Preview(showBackground = true, device = Devices.TV_1080p)
@Composable
fun MovieCardPreview() {
    MovieCard(
        Movie(
            1,
            "Movie Title",
            "Movie description",
            Movie.url,

            GenreTypes.Action.drawable,
            "2 hours",
            8.5f
        )
    )
}
