package org.marproject.moviescatalogue.view.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

class FavoriteTvShowViewModel(private val moviesRepository: MoviesRepository) : ViewModel()  {

    fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>> = moviesRepository.getFavoriteTvShows()

    fun setFavorite(tvShow: MovieEntity) {
        val newState = !tvShow.is_favorite
        moviesRepository.setFavoriteMovie(tvShow, newState)
    }
}