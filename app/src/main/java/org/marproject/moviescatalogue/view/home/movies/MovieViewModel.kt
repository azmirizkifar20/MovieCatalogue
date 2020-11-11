package org.marproject.moviescatalogue.view.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.vo.Resource

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getMoviesData(): LiveData<Resource<PagedList<MovieEntity>>> = moviesRepository.getAllMovies()

    fun getSortedMovies(sort: String): LiveData<PagedList<MovieEntity>> = moviesRepository.getSortedMovies(sort)

}