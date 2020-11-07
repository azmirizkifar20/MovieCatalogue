package org.marproject.moviescatalogue.view.detail

import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.dummy.DataDummy
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(id: String) {
        this.movieId = id
    }

    fun setSelectedTvShow(id: String) {
        this.tvShowId = id
    }

    fun getMovieDetail(): MovieEntity = moviesRepository.getDetailMovie(movieId)

    fun getTvShowDetail(): MovieEntity = moviesRepository.getDetailTvShow(tvShowId)
}