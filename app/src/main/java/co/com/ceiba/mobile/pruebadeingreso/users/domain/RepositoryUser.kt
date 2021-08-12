package co.com.ceiba.mobile.pruebadeingreso.users.domain

import co.com.ceiba.mobile.pruebadeingreso.common.Result
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse

interface RepositoryUser {
    suspend fun getUsersList(): Result<List<UserEntity>, Exception>
    suspend fun getUserPost(id: Int): Result<List<UserPostResponse>, Exception>
}