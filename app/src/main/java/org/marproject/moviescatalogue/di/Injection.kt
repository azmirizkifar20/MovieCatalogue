package org.marproject.moviescatalogue.di

import android.content.Context
import org.marproject.moviescatalogue.data.source.MoviesRepository
import org.marproject.moviescatalogue.data.source.remote.RemoteDataSource
import org.marproject.moviescatalogue.utils.helper.JsonHelper

object Injection {
    fun provideRepository(context: Context): MoviesRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MoviesRepository.getInstance(remoteDataSource)
    }
}