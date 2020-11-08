package org.marproject.moviescatalogue.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.remote.RemoteDataSource
import org.marproject.moviescatalogue.utils.helper.JsonHelper
import org.marproject.moviescatalogue.view.detail.DetailViewModel
import org.marproject.moviescatalogue.view.movies.MovieViewModel
import org.marproject.moviescatalogue.view.tvshow.TvShowViewModel

val helperModule = module {
    factory { JsonHelper(get()) }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { MoviesRepository(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}