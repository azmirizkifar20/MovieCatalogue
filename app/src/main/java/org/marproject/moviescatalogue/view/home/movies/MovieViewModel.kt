package org.marproject.moviescatalogue.view.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.vo.Resource

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getMoviesData(): LiveData<Resource<List<MovieEntity>>> = moviesRepository.getAllMovies()
}