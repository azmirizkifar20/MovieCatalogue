package org.marproject.moviescatalogue.view.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getMoviesData(): LiveData<List<MovieEntity>> = moviesRepository.getAllMovies()
}