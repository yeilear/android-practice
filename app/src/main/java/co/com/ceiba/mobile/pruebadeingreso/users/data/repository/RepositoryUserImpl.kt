package co.com.ceiba.mobile.pruebadeingreso.users.data.repository

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Result
import co.com.ceiba.mobile.pruebadeingreso.common.network.NetworkManagerState
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.ErrorResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.UserApi
import co.com.ceiba.mobile.pruebadeingreso.users.domain.RepositoryUser

private const val NETWORK_ERROR = "Network Error"

class RepositoryUserImpl(private val _remote: UserApi,
                         private val _networkManagerState: NetworkManagerState) : RepositoryUser {

    override suspend fun getUsersList(): Result<List<UserListResponse>, Exception> {
        return if(_networkManagerState.isConnected())
            _remote.getUsersList()
        else
            Failure(ErrorResponse(NETWORK_ERROR))
    }

    override suspend fun getUserPost(id: Int): Result<List<UserPostResponse>, Exception> {
        return if(_networkManagerState.isConnected())
            _remote.getUserPost(id)
        else
            Failure(ErrorResponse(NETWORK_ERROR))
    }
}