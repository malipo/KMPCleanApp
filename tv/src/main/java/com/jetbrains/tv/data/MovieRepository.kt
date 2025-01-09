package com.jetbrains.tv.data

import com.jetbrains.tv.data.model.Genre
import com.jetbrains.tv.data.model.GenreTypes
import com.jetbrains.tv.data.model.Movie
import com.jetbrains.tv.data.util.GenreJsonParser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface MovieRepository {
    suspend fun retrieveGenres(): List<Genre>
    suspend fun retrieveMovie(id: Int): Movie?
    suspend fun retrieveFeaturedMovies(): List<Movie> // New function
}

class MovieRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val genreJsonParser: GenreJsonParser
) : MovieRepository {

    override suspend fun retrieveGenres(): List<Genre> = withContext(dispatcher) {
        genreJsonParser.readMoviesFromAssets().map {
            it.movies = it.movies.map { movie ->
                movie.thumbnail = GenreTypes.fromId(it.id).drawable
                movie
            }
            it
        }
    }

    override suspend fun retrieveMovie(id: Int): Movie? = withContext(dispatcher) {
        genreJsonParser.readMoviesFromAssets().flatMap {
            it.movies = it.movies.map { movie ->
                movie.thumbnail = GenreTypes.fromId(it.id).drawable
                movie
            }
            it.movies
        }.firstOrNull { it.id == id }
    }

    override suspend fun retrieveFeaturedMovies(): List<Movie> = withContext(dispatcher) {
        // Choose the first movie from each genre as featured movies
        genreJsonParser.readMoviesFromAssets()
            .flatMap { it.movies }
            .shuffled()
            .take(5) // Select top 5 movies as featured
    }
}