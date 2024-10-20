package com.jetbrains.kmpcleanapp.android

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.museumlist.presentation.MuseumUiState
import com.jetbrains.museumlist.presentation.MuseumViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreenRoute(
    viewModel: MuseumViewModel = koinViewModel()
) {
    val museumUiState by viewModel.museumUiState.collectAsState()

    ListScreen(uiState = museumUiState)
}

@Composable
fun ListScreen(uiState: MuseumUiState) {
    AnimatedContent(uiState.isLoading || uiState.museums.isNotEmpty()) { objectsAvailable ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    Modifier
                        .size(30.dp)
                        .align(Alignment.Center))
            }
        } else if (objectsAvailable) {

            ObjectGrid(objects = uiState.museums)
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ObjectGrid(objects: List<MuseumDTO>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Horizontal)
                    .asPaddingValues()
            ),
        contentPadding = WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
            .asPaddingValues(),
    ) {
        items(objects.size, key = { objects[it].objectID }) { index ->
            val obj = objects[index]
            ObjectFrame(obj = obj)
        }

    }
}

@Composable
private fun ObjectFrame(obj: MuseumDTO, modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(8.dp)
    ) {
        AsyncImage(
            model = obj.primaryImage,
            contentDescription = obj.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(ratio = 0.67f),
        )

        Spacer(Modifier.height(2.dp))

        Text(obj.title)
        Text(obj.artistDisplayName)
        Text(obj.objectDate)
    }
}

@Composable
fun EmptyScreenContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text("no data ")
    }
}
