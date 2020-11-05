package org.marproject.moviescatalogue.view.movies

import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.DataDummy
import org.marproject.moviescatalogue.model.Movies

class MovieViewModel : ViewModel() {
    fun getMoviewData(): List<Movies> = DataDummy.moviesData()
}