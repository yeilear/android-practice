package co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Result
import co.com.ceiba.mobile.pruebadeingreso.common.Success
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.ErrorResponse
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserListResponse
import co.com.ceiba.mobile.pruebadeingreso.users.domain.RepositoryUser
import co.com.ceiba.mobile.pruebadeingreso.users.domain.entity.UserMessageResponse
import java.lang.Exception

class GetUserListUseCase(private val repositoryUser: RepositoryUser) {

    suspend operator fun invoke(): Result<List<UserListResponse>, Exception> {

        return when (val result = repositoryUser.getUsersList()){

            is Success -> Success(result.value)
            is Failure -> {
                if (result.reason is ErrorResponse) Failure(ErrorResponse(UserMessageResponse.SYSTEM_ERROR.message)) else (Failure(result.reason))
            }
        }
    }
}