package org.marproject.moviescatalogue.view.movies

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMoviewData() {
        val movieEntities = viewModel.getMoviesData()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }
}