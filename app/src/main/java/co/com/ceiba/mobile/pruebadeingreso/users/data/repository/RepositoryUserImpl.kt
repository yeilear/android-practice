package co.com.ceiba.mobile.pruebadeingreso.users.data.repository

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Result
import co.com.ceiba.mobile.pruebadeingreso.common.Success
import co.com.ceiba.mobile.pruebadeingreso.common.network.NetworkManagerState
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.RoomUserLocal
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.ErrorResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.UserApi
import co.com.ceiba.mobile.pruebadeingreso.users.domain.RepositoryUser

private const val NETWORK_ERROR = "Network Error"

class RepositoryUserImpl(private val _userLocal: RoomUserLocal,
                         private val _remote: UserApi,
                         private val _networkManagerState: NetworkManagerState) : RepositoryUser {

    override suspend fun getUsersList(): Result<List<UserEntity>, Exception> {

        val resultDao = _userLocal.readAllUsers()
        return when {
            resultDao.isNotEmpty() -> Success(resultDao)
            _networkManagerState.isConnected() -> {
                val resultRemote = _remote.getUsersList()
                if (resultRemote is Success) _userLocal.addUser(
                    resultRemote.value
                )
                resultRemote
            }
            else -> Failure(ErrorResponse(NETWORK_ERROR))
        }

    }

    override suspend fun getUserPost(id: Int): Result<List<UserPostResponse>, Exception> {
        return if(_networkManagerState.isConnected())
            _remote.getUserPost(id)
        else
            Failure(ErrorResponse(NETWORK_ERROR))
    }
}