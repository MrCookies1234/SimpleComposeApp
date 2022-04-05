package com.example.simplecomposeapp.model

data class ApiResponse(val results: List<Results>)


data class Results(
    val name: UserName?,
    val location: UserLocation?,
    val picture: UserPicture?,
)