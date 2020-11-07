package org.marproject.moviescatalogue.view.tvshow

import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.dummy.DataDummy
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

class TvShowViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getTvShowData(): List<MovieEntity> = moviesRepository.getAllTvShows()
}