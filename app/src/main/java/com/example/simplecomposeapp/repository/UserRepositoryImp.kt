package com.example.simplecomposeapp.repository

import com.example.simplecomposeapp.datasource.RestDataSource
import com.example.simplecomposeapp.model.User
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val datasource: RestDataSource): UserRepository {

    override suspend fun getUser(): User{
        val name= datasource.getUserName().results[0].userName!!
        val location = datasource.getUserLocation().results[0].userLocation!!
        val picture = datasource.getUserPicture().results[0].picture!!
        return User(name.first, name.last, location.city, picture.thumbnail)
    }
}