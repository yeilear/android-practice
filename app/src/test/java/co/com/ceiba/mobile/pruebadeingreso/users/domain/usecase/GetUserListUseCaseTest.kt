package co.com.ceiba.mobile.pruebadeingreso.users.domain.usecase

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Success
import co.com.ceiba.mobile.pruebadeingreso.users.UserTestFactory
import co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote.ErrorResponse
import co.com.ceiba.mobile.pruebadeingreso.users.domain.RepositoryUser
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner.Silent::class)
class GetUserListUseCaseTest : TestCase(){

    @Mock
    private lateinit var _repository: RepositoryUser

    @InjectMocks
    private lateinit var _getUserListUseCase: GetUserListUseCase

    @Test
    fun `when getUserListUseCase is Success`() {

        runBlocking {
            val expect = false

            Mockito.`when`(_repository.getUsersList())
                .thenReturn(Success(UserTestFactory.createResponseUsers()))

            when (  val result = _getUserListUseCase()) {
                is Success -> assertEquals(result.value.isEmpty(), expect)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when getUserListUseCase repository failed then return ErrorResponse`() {

        runBlocking {

            Mockito.`when`(_repository.getUsersList())
                .thenReturn(Failure(ErrorResponse("test exception")))

            when (  val result = _getUserListUseCase()) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when getUserListUseCase repository failed then return Exception`() {

        runBlocking {

            Mockito.`when`(_repository.getUsersList())
                .thenReturn(Failure(Exception("test exception")))

            when (_getUserListUseCase()) {

                is Failure -> assert(true)
                else -> assert(false)
            }
        }
    }
}