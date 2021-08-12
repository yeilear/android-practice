package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote

import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserListResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse
import co.com.ceiba.mobile.pruebadeingreso.common.Result


interface UserApi {
    suspend fun getUsersList(): Result<List<UserListResponse>, Exception>
    suspend fun getUserPost(id: Int): Result<List<UserPostResponse>, Exception>
}