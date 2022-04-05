package com.example.simplecomposeapp.di

import com.example.simplecomposeapp.repository.UserRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Provides
    abstract fun userRepository(repo: UserRepositoryImp) : UserRepositoryImp

}