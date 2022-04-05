package com.example.simplecomposeapp.repository

import androidx.lifecycle.LiveData
import com.example.simplecomposeapp.model.User

interface UserRepository {
    suspend fun getUser(): User
    suspend fun deleteUser(usertoDelete : User)
    fun getAllUsers() : LiveData<List<User>>

}