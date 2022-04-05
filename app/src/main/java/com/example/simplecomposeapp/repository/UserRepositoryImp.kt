package com.example.simplecomposeapp.repository

import androidx.lifecycle.LiveData
import com.example.simplecomposeapp.datasource.RestDataSource
import com.example.simplecomposeapp.model.User
import com.example.simplecomposeapp.model.UserDAO
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val datasource: RestDataSource,
    private val userDao: UserDAO): UserRepository {

    override suspend fun getUser(): User{
        val name= datasource.getUserName().results[0].name!!
        val location = datasource.getUserLocation().results[0].location!!
        val picture = datasource.getUserPicture().results[0].picture!!
        val user = User(name.first, name.last, location.city, picture.thumbnail)
        userDao.insertUser(user)
        return user
    }

    override suspend fun deleteUser(usertoDelete: User) {
        userDao.deleteUser(usertoDelete)
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }


}