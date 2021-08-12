package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.HttpCode
import co.com.ceiba.mobile.pruebadeingreso.common.Result
import co.com.ceiba.mobile.pruebadeingreso.common.Success
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.local.model.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.users.data.repository.UserPostResponse

private const val TIME_OUT = "Time out Network"
private const val ERROR_PARSE_ELEMENT = "Error to Parse element"
private const val UN_AUTHORIZED = "UnAuthorized request"
private const val UN_KNOW_ERROR = "Un know error"
private const val EXCEPTION_ERROR = "Exception"
class UserApiImpl(private  val _retrofitUsersApi: RetrofitUsersApi): UserApi {

    override suspend fun getUsersList(): Result<List<UserEntity>, Exception> {
        try {
            val result = _retrofitUsersApi.getUsersList()

            return when (result.code()) {
                HttpCode.OK.code -> {
                    result.body()?.let {
                        return Success(it)
                    } ?: kotlin.run {
                        return Failure(ErrorResponse(ERROR_PARSE_ELEMENT))
                    }
                }
                HttpCode.TIME_OUT.code -> Failure(ErrorResponse(TIME_OUT))
                HttpCode.BAD_REQUEST.code -> Failure(ErrorResponse(ERROR_PARSE_ELEMENT))
                HttpCode.UNAUTHORIZED.code -> Failure(ErrorResponse(UN_AUTHORIZED))
                else -> Failure(ErrorResponse(UN_KNOW_ERROR))
            }
        } catch (e: Exception) {
            return Failure(ErrorResponse(e.localizedMessage ?: EXCEPTION_ERROR))
        }
    }

    override suspend fun getUserPost(id: Int): Result<List<UserPostResponse>, Exception> {
        try {
            val result = _retrofitUsersApi.getUserPost(id)

            return when (result.code()) {
                HttpCode.OK.code -> {
                    result.body()?.let {
                        return Success(it)
                    } ?: kotlin.run {
                        return Failure(ErrorResponse(ERROR_PARSE_ELEMENT))
                    }
                }
                HttpCode.TIME_OUT.code -> Failure(ErrorResponse(TIME_OUT))
                HttpCode.BAD_REQUEST.code -> Failure(ErrorResponse(ERROR_PARSE_ELEMENT))
                HttpCode.UNAUTHORIZED.code -> Failure(ErrorResponse(UN_AUTHORIZED))
                else -> Failure(ErrorResponse(UN_KNOW_ERROR))
            }
        } catch (e: Exception) {
            return Failure(ErrorResponse(e.localizedMessage ?: EXCEPTION_ERROR))
        }
    }


}