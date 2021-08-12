package co.com.ceiba.mobile.pruebadeingreso.users.data.repository

import com.squareup.moshi.Json

class UserPostResponse(
        @field: Json(name = "id") val id: Int,
        @field: Json(name = "title") val title: String,
        @field: Json(name = "body") val body: String)