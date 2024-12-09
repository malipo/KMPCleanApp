package com.jetbrains.tv.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TVScreen() {
    val textList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    LazyColumn {
        items(
            count = textList.size,
            key = { index -> index }
        ) { index ->
            Text(
                text = textList[index],
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
//
//@Composable
//fun CatalogBrowser(
//    featuredContentList: List<Movie>,
//    sectionList: List<Section>,
//    modifier: Modifier = Modifier,
//    onItemSelected: (Movie) -> Unit = {},
//) {
//    LazyColumn(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        items(sectionList) { section ->
//            Section(section, onItemSelected = onItemSelected)
//        }
//    }
//}
//
//@Composable
//fun Section(
//    section: Section,
//    modifier: Modifier = Modifier,
//    onItemSelected: (Movie) -> Unit = {},
//) {
//    Text(
//        text = section.title,
//        style = MaterialTheme.typography.headlineSmall,
//    )
//    LazyRow(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(section.movieList){ movie ->
//            MovieCard(
//                movie = movie,
//                onClick = { onItemSelected(movie) }
//            )
//        }
//    }
//}
//
//@Composable
//fun MovieCard(
//    movie: Movie,
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit = {}
//) {
//    Card(modifier = modifier, onClick = onClick){
//        AsyncImage(
//            model = movie.thumbnailUrl,
//            contentDescription = movie.title,
//        )
//    }
//}