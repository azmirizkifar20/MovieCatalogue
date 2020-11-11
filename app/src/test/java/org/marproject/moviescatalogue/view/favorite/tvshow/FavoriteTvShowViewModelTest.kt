package org.marproject.moviescatalogue.view.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.local.entity.MovieEntity
import org.marproject.moviescatalogue.utils.helper.DataDummy
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyFavoriteTvShow = DataDummy.tvShowData()
        val favoriteTvShow = MutableLiveData<List<MovieEntity>>()
        favoriteTvShow.value = dummyFavoriteTvShow

        `when`(moviesRepository.getFavoriteTvShows()).thenReturn(favoriteTvShow)
        val favoriteTvShowEntities = viewModel.getFavoriteTvShows().value
        verify(moviesRepository).getFavoriteTvShows()
        assertNotNull(favoriteTvShowEntities)
        assertEquals(10, favoriteTvShowEntities?.size)

        viewModel.getFavoriteTvShows().observeForever(observer)
        verify(observer).onChanged(dummyFavoriteTvShow)
    }
}