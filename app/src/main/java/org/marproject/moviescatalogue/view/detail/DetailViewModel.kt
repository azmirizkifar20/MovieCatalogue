package org.marproject.moviescatalogue.view.detail

import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.DataDummy
import org.marproject.moviescatalogue.model.Movies

class DetailViewModel : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(id: String) {
        this.movieId = id
    }

    fun setSelectedTvShow(id: String) {
        this.tvShowId = id
    }

    fun getMovieDetail(): Movies? {
        var movie: Movies? = null
        for (movieEntity in DataDummy.moviesData()) {
            if (movieEntity.id == movieId) movie = movieEntity
        }

        return movie
    }

    fun getTvShowDetail(): Movies? {
        var tvShow: Movies? = null
        for (tvShowEntity in DataDummy.tvShowData()) {
            if (tvShowEntity.id == tvShowId) tvShow = tvShowEntity
        }

        return tvShow
    }
}