package org.marproject.moviescatalogue.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.vo.Resource

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(id: String) {
        this.movieId = id
    }

    fun setSelectedTvShow(id: String) {
        this.tvShowId = id
    }

    fun getMovieDetail(): LiveData<Resource<MovieEntity>> = moviesRepository.getDetailMovie(movieId)

    fun getTvShowDetail(): LiveData<Resource<MovieEntity>> = moviesRepository.getDetailTvShow(tvShowId)

    fun setBookmark(movie: MovieEntity, status: Boolean) = moviesRepository.setFavoriteMovie(movie, status)
}