package org.marproject.moviescatalogue.view.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel()  {
    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = moviesRepository.getFavoriteMovies()
}