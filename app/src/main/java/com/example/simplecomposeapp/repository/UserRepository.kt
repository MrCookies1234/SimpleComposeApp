package com.example.simplecomposeapp.repository

import com.example.simplecomposeapp.model.User

interface UserRepository {
    suspend fun getUser(): User
}