package br.itcampos.moviedatabase.di

import br.itcampos.moviedatabase.data.local.database.UserRepositoryImpl
import br.itcampos.moviedatabase.data.repository.MovieDbRepositoryImpl
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDbRepositoryBind {

    @Binds
    @Singleton
    abstract fun bindRepository(movieDbRepositoryImpl: MovieDbRepositoryImpl) : MovieDbRepository

}