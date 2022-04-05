package com.example.simplecomposeapp.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplecomposeapp.model.User
import com.example.simplecomposeapp.model.UserDAO

@Database(entities = [User::class], version = 1)
abstract class DBDataSource: RoomDatabase() {

    abstract fun  userDAO(): UserDAO
}