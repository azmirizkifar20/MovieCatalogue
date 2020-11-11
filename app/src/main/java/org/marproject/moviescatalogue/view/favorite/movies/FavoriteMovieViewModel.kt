package org.marproject.moviescatalogue.view.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel()  {

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = moviesRepository.getFavoriteMovies()

    fun setFavorite(movie: MovieEntity) {
        val newState = !movie.is_favorite
        moviesRepository.setFavoriteMovie(movie, newState)
    }
}