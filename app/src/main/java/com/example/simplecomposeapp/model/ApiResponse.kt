package com.example.simplecomposeapp.model

class ApiResponse {
    val results: List<Results> = emptyList()
}

data class Results(
    val userLocation: UserLocation?,
    val userName: UserName?,
    val picture: UserPicture?
)