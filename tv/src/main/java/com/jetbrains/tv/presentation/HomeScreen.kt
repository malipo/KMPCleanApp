package com.jetbrains.tv.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.Button
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.jetbrains.tv.presentation.components.GenreRow
import com.jetbrains.tv.data.model.Movie
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onMovieSelected: (Movie) -> Unit,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val featuredMovies = viewModel.featuredMovies.value
    val uiState = viewModel.uiState.value

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        // Genres list
        items(count = uiState.genres.size) { index ->
            val genre = uiState.genres[index]
            GenreRow(
                genre,
                onItemSelected = { onMovieSelected(it) },
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}
