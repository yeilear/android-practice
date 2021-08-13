package co.com.ceiba.mobile.pruebadeingreso.users.data.datasource.remote

import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.Success
import co.com.ceiba.mobile.pruebadeingreso.users.UserTestFactory
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserApiImplTest : TestCase(){

    @Mock
    private lateinit var _retrofit: RetrofitUsersApi

    @InjectMocks
    private lateinit var _userApiImpl: UserApiImpl

    @Test
    fun `when success with http 200 getUserList`() {
        runBlocking {
            //Arrange
            val expect = false
            val request = UserTestFactory.createSuccessfulResponse(200)

            Mockito.`when`(_retrofit.getUsersList())
                .thenReturn(request)

            //assert
            when ( val result = _userApiImpl.getUsersList()) {

                is Success -> assertEquals(result.value.isEmpty(), expect)
                else -> assert(false)
            }

        }
    }

    @Test
    fun `when success with http code 200 and null body to parse getUserList`() {
        runBlocking {
            //Arrange
            val response = UserTestFactory.createSuccessResponseRequestNullBody(200)

            Mockito.`when`(_retrofit.getUsersList()).thenReturn(response)

            //assert
            when ( val result = _userApiImpl.getUsersList()) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }

        }
    }

    @Test
    fun `when response bad request 400 getUserList`() {
        runBlocking {
            val response = UserTestFactory.createFailureResponseRequestUsers(400)

            Mockito.`when`(_retrofit.getUsersList()).thenReturn(response)

            //assert
            when ( val result = _userApiImpl.getUsersList()) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }

        }
    }

    @Test
    fun `when response un authorized 401 getUserList`() {
        runBlocking {

            val response = UserTestFactory.createFailureResponseRequestUsers(401)

            Mockito.`when`(_retrofit.getUsersList()).thenReturn(response)

            when ( val result = _userApiImpl.getUsersList()) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when response uncontrolled response getUserList`() {
        runBlocking {

            val response = UserTestFactory.createFailureResponseRequestUsers(500)

            Mockito.`when`(_retrofit.getUsersList()).thenReturn(response)

            when ( val result = _userApiImpl.getUsersList()) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when response Exception response getUserList`() {
        runBlocking {
            val response = null
            Mockito.`when`(_retrofit.getUsersList()).thenReturn(response)


            when (val result = _userApiImpl.getUsersList()) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when success with http 200 getUserPost`() {
        runBlocking {
            //Arrange
            val expect = false
            val id = 1
            val request = UserTestFactory.createSuccessfulResponsePost(200)

            Mockito.`when`(_retrofit.getUserPost(id))
                .thenReturn(request)

            //assert
            when ( val result = _userApiImpl.getUserPost(id)) {

                is Success -> assertEquals(result.value.isEmpty(), expect)
                else -> assert(false)
            }

        }
    }

    @Test
    fun `when success with http code 200 and null body to parse getUserPost`() {
        runBlocking {
            //Arrange
            val id = 1
            val response = UserTestFactory.createSuccessResponseRequestPostsNullBody(200)

            Mockito.`when`(_retrofit.getUserPost(id)).thenReturn(response)

            //assert
            when ( val result = _userApiImpl.getUserPost(id)) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }

        }
    }

    @Test
    fun `when response bad request 400 getUserPost`() {
        runBlocking {
            val id = 1
            val response = UserTestFactory.createFailureResponseRequestPosts(400)

            Mockito.`when`(_retrofit.getUserPost(id)).thenReturn(response)

            //assert
            when ( val result = _userApiImpl.getUserPost(id)) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }

        }
    }

    @Test
    fun `when response un authorized 401 getUserPost`() {
        runBlocking {

            val id = 1
            val response = UserTestFactory.createFailureResponseRequestPosts(401)

            Mockito.`when`(_retrofit.getUserPost(id)).thenReturn(response)

            //assert
            when ( val result = _userApiImpl.getUserPost(id)) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when response uncontrolled response getUserPost`() {
        runBlocking {

            val id = 1
            val response = UserTestFactory.createFailureResponseRequestPosts(500)

            Mockito.`when`(_retrofit.getUserPost(id)).thenReturn(response)

            //assert
            when ( val result = _userApiImpl.getUserPost(id)) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

    @Test
    fun `when response Exception response getUserPost`() {
        runBlocking {
            val id = 1
            val response = null

            Mockito.`when`(_retrofit.getUserPost(id)).thenReturn(response)

            //assert
            when ( val result = _userApiImpl.getUserPost(id)) {

                is Failure -> assert(result.reason is ErrorResponse)
                else -> assert(false)
            }
        }
    }

}