package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote

import co.com.ceiba.mobile.pruebadeingreso.common.Result
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserListResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse


class UserApiImpl(private  val _retrofitUsersApi: RetrofitUsersApi): UserApi {

    override suspend fun getUsersList(): Result<List<UserListResponse>, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPost(id: Int): Result<List<UserPostResponse>, Exception> {
        TODO("Not yet implemented")
    }


}