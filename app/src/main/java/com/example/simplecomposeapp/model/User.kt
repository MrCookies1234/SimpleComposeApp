package com.example.simplecomposeapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name="name")val name: String,
    @ColumnInfo(name="lastname")val lastName: String,
    @ColumnInfo(name="city")val city: String,
    @ColumnInfo(name="thumbnail")val thumbnail: String,
    @PrimaryKey (autoGenerate = true) var id: Int = 0
)

@Dao
interface UserDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Delete
    fun deleteUser(user: User)
}