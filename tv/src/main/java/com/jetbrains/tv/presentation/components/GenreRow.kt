package com.jetbrains.tv.presentation.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.jetbrains.tv.data.model.Genre
import com.jetbrains.tv.data.model.Movie
import kotlinx.coroutines.launch

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun GenreRow(
    genre: Genre,
    modifier: Modifier = Modifier,
    onItemSelected: (Movie) -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = genre.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        val focusRequester = remember { FocusRequester() }
        val tvLazyListState = rememberTvLazyListState()
        val coroutineScope = rememberCoroutineScope()

        TvLazyRow(
            modifier = modifier
                .focusRequester(focusRequester)
                .focusable()
                .onFocusChanged { state ->
                    if (state.isFocused) {
                        coroutineScope.launch {
                            tvLazyListState.scrollToItem(0)
                        }
                    }
                },
            state = tvLazyListState,
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(genre.movies) { movie ->
                var isFocused by remember { mutableStateOf(false) }

                MovieCard(
                    movie = movie,
                    modifier = Modifier
                        .focusable()
                        .onFocusChanged { state -> isFocused = state.isFocused }
                        .graphicsLayer(
                            scaleX = if (isFocused) 1.1f else 1f,
                            scaleY = if (isFocused) 1.1f else 1f,
                        ),
                    onClick = { onItemSelected(movie) }
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.TV_1080p)
@Composable
fun GenreRowPreview() {
    GenreRow(Genre(1, "Action", emptyList()))
}
