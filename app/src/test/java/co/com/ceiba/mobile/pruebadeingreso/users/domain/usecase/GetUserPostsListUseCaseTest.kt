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
class GetUserPostsListUseCaseTest : TestCase(){

    @Mock
    private lateinit var _repository: RepositoryUser

    @InjectMocks
    private lateinit var _getUserPostsListUseCase: GetUserPostsListUseCase

    @Test
    fun `when getUserPostsListUseCase is Success`() {

        runBlocking {
            val id = 1
            val expect = false

            Mockito.`when`(_repository.getUserPost(id))
                .thenReturn(Success(UserTestFactory.createResponsePosts()))

            when (  val result = _getUserPostsListUseCase(id)) {
                is Success -> assertEquals(result.value.isEmpty(), expect)
                else -> assert(false)
            }
        }
    }

   @Test
    fun `when getUserPostsListUseCase repository failed then return ErrorResponse`() {

        runBlocking {
            val id = 1
            Mockito.`when`(_repository.getUserPost(id))
                .thenReturn(Failure(ErrorResponse("test exception")))

            when (  val result = _getUserPostsListUseCase(id)) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when getUserPostsListUseCase repository failed then return Exception`() {

        runBlocking {
            val id = 1
            Mockito.`when`(_repository.getUserPost(id))
                .thenReturn(Failure(Exception("test exception")))

            when (_getUserPostsListUseCase(id)) {

                is Failure -> assert(true)
                else -> assert(false)
            }
        }
    }
}