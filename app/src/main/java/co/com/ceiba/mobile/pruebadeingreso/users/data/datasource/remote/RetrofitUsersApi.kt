package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote

import co.com.ceiba.mobile.pruebadeingreso.common.GET_POST_USER
import co.com.ceiba.mobile.pruebadeingreso.common.GET_USERS
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserListResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitUsersApi {

    @GET(GET_USERS)
    suspend fun getUsersList(): Response<List<UserListResponse>>

    @GET(GET_POST_USER)
    suspend fun getUserPost(@Query("id") id: Int): Response<List<UserPostResponse>>
}