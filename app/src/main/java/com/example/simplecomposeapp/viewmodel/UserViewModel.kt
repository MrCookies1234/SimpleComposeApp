package com.example.simplecomposeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplecomposeapp.model.User
import com.example.simplecomposeapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepositoryImp: UserRepository): ViewModel() {

   private val _isLoading: MutableLiveData<Boolean> by lazy {
       MutableLiveData<Boolean>(false)
   }

    val users: LiveData<List<User>> by lazy {
        userRepositoryImp.getAllUsers()
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addUser(){
        if(_isLoading.value == false){
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                userRepositoryImp.getUser()
                _isLoading.postValue(false)
            }
        }
    }

    fun deleteUser(userToDelete: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepositoryImp.deleteUser(userToDelete)
        }
    }
}