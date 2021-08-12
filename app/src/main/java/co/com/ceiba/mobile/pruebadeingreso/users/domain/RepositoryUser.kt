package co.com.ceiba.mobile.pruebadeingreso.users.domain

import co.com.ceiba.mobile.pruebadeingreso.common.Result
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserListResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse

interface RepositoryUser {
    suspend fun getUsersList(): Result<List<UserListResponse>, Exception>
    suspend fun getUserPost(id: Int): Result<List<UserPostResponse>, Exception>
}