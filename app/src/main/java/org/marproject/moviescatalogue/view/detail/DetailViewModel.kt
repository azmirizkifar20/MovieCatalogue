package org.marproject.moviescatalogue.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.vo.Resource

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var movieId = MutableLiveData<String>()
    private var tvShowId = MutableLiveData<String>()

    fun setSelectedMovie(id: String) {
        this.movieId.value = id
    }

    fun setSelectedTvShow(id: String) {
        this.tvShowId.value = id
    }

    var movie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { movieId ->
        moviesRepository.getDetailMovie(movieId)
    }

    var tvShow: LiveData<Resource<MovieEntity>> = Transformations.switchMap(tvShowId) { tvShowId ->
        moviesRepository.getDetailTvShow(tvShowId)
    }

    fun setBookmark() {
        val resourceMovie = movie.value
        val resourceTvShow = tvShow.value

        if (resourceMovie != null) {
            val movieData = resourceMovie.data

            if (movieData != null) {
                val newState = !movieData.is_favorite
                moviesRepository.setFavoriteMovie(movieData, newState)
            }
        }

        if (resourceTvShow != null) {
            val tvShowData = resourceTvShow.data

            if (tvShowData != null) {
                val newState = !tvShowData.is_favorite
                moviesRepository.setFavoriteMovie(tvShowData, newState)
            }
        }
    }

}