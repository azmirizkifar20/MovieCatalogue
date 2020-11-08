package org.marproject.moviescatalogue.view.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity

class TvShowViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getTvShowData(): LiveData<List<MovieEntity>> = moviesRepository.getAllTvShows()
}