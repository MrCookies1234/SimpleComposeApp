package com.example.simplecomposeapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplecomposeapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepositoryImp: UserRepository): ViewModel() {

    fun getUser(){
        viewModelScope.launch(Dispatchers.IO){
            val user = userRepositoryImp.getUser()
            Log.d("USER: ", user.toString())

        }
    }
}