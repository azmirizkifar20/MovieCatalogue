package org.marproject.moviescatalogue

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.marproject.moviescatalogue.di.helperModule
import org.marproject.moviescatalogue.di.repositoryModule
import org.marproject.moviescatalogue.di.viewModelModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    helperModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}