package org.marproject.moviescatalogue.view.tvshow

import androidx.lifecycle.ViewModel
import org.marproject.moviescatalogue.data.DataDummy
import org.marproject.moviescatalogue.model.Movies

class TvShowViewModel : ViewModel() {
    fun getTvShowData(): List<Movies> = DataDummy.tvShowData()
}