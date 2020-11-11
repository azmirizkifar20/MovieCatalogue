package org.marproject.moviescatalogue.view.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.vo.Resource

class TvShowViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getTvShowData(): LiveData<Resource<PagedList<MovieEntity>>> = moviesRepository.getAllTvShows()
}