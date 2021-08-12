package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote

import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse
import co.com.ceiba.mobile.pruebadeingreso.common.Result
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity


interface UserApi {
    suspend fun getUsersList(): Result<List<UserEntity>, Exception>
    suspend fun getUserPost(id: Int): Result<List<UserPostResponse>, Exception>
}