package org.marproject.moviescatalogue.view.tvshow

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShowData() {
        val tvShowEntities = viewModel.getTvShowData()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}